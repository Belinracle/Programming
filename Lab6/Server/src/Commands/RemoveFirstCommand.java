package Commands;

import Collection.CollectionInterface;
import IO.IOinterface;

import java.io.IOException;
import java.util.List;
import Connection.*;
public class RemoveFirstCommand implements Command {
    private CollectionInterface coll;
    private Validator val = new Validator("Any");
    private String name;
    RemoveFirstCommand(CollectionInterface coll, CommandFetch cf){
        cf.addCommand("remove_first",this);
        this.coll = coll;
        name="remove_first";
    }
    @Override
    public void execute(IOinterface io, List<String> args) throws IOException {
        coll.remove_first(io);
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
