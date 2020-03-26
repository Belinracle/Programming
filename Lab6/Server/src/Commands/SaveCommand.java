package Commands;
import Connection.*;
import Collection.CollectionInterface;
import Factory.IDFactory;
import IO.IOconsole;
import IO.IOinterface;
import Parsers.Parser;

import java.io.*;
import java.util.List;

public class SaveCommand implements Command {
    private Validator val = new Validator("Any");
    private CollectionInterface ci;
    private String path;
    private Parser parser;
    private IDFactory id;
    private String name;
    SaveCommand(CollectionInterface ci, String path,CommandFetch cf, Parser parser) throws IOException {
        this.ci=ci;
        this.path=path;
        this.parser=parser;
        name="save";
        cf.addCommand("save",this);
        id= new IDFactory("IdContainer.txt");
    }
    @Override
    public void execute(IOinterface io, List<String> args) throws IOException {
        IOinterface file = new IOconsole(System.in,new FileOutputStream(new File(path)),true);
        ci.save(parser,file);
        file.close();
        id.write(String.valueOf(IDFactory.getCurrentID()));
        io.writeln("Коллекция сохранена");
    }

    @Override
    public Validator getValidator() {
        return val;
    }

    @Override
    public String getName() {
        return name;
    }
}
