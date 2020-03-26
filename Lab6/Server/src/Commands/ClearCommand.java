package Commands;

import Collection.CollectionInterface;
import IO.IOinterface;
import Connection.*;
import java.io.IOException;
import java.util.List;

public class ClearCommand implements Command {
    private CollectionInterface ci;
    private Validator val  = new Validator("Any");
    private String name;
    public ClearCommand(CollectionInterface ci, CommandFetch cf){
        cf.addCommand("clear",this);
        this.ci=ci;
        name="clear";
    }
    @Override
    public void execute(IOinterface io, List<String> args) throws IOException {
        ci.clear();
        io.writeln("Коллекция очищена");
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
