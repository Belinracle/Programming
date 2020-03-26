package Commands;

import Collection.CollectionInterface;
import IO.IOinterface;

import java.io.IOException;
import java.util.List;
import Connection.*;
public class InfoCommand implements Command {
    private CollectionInterface coll;
    private Validator val = new Validator("Any");
    private String name;
    InfoCommand(CollectionInterface coll, CommandFetch cf){
        this.coll = coll;
        cf.addCommand("info",this);
        name="info";
    }
    @Override
    public void execute(IOinterface io, List<String> args) throws IOException {
        io.writeln(coll.info());
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
