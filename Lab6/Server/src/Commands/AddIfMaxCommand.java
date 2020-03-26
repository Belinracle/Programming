package Commands;

import Collection.CollectionInterface;
import Connection.*;
import Factory.Factory;
import IO.IOinterface;

import java.io.IOException;
import java.util.List;

public class AddIfMaxCommand implements Command {
    private CollectionInterface ci;
    private Factory fac;
    private Validator val = new Validator("Any");
    private String name;
    AddIfMaxCommand(CollectionInterface ci, CommandFetch cf, Factory fac){
        cf.addCommand("add_if_max",this);
        this.ci=ci;
        val.movie();
        name="add_if_max";
    }
    @Override
    public void execute(IOinterface io, List<String> args) throws IOException, ClassNotFoundException {
        ci.add_if_max(fac.createMovie(io,args),io);
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
