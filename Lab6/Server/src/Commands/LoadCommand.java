package Commands;

import Collection.CollectionInterface;
import IO.IOconsole;
import IO.IOinterface;
import Parsers.Parser;

import java.io.*;
import java.util.List;
import Connection.*;
public class LoadCommand implements Command {
    private Validator val = new Validator("Path");
    private CollectionInterface ci;
    private Parser pars;
    private String name;
    LoadCommand(CollectionInterface ci, Parser pars, CommandFetch cf){
        cf.addCommand("load",this);
        this.ci = ci;
        this.pars=pars;
        name="load";
    }
    @Override
    public void execute(IOinterface io, List<String> args) throws IOException {
        try {
            if (val.validate(args)) {
                IOinterface ioFile = new IOconsole(new FileInputStream(new File(args.get(1))), System.out, true);
                ci.load(pars,ioFile);
                io.writeln("Коллекция загружена");
            } else io.writeln("Неверные аргументы команды(Указан неверный файл)");
        }catch (NumberFormatException e){
            io.writeln("В файле нет объектов, загружена пустая коллекция");
        }
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
