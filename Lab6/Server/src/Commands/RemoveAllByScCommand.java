package Commands;

import Collection.CollectionInterface;
import Factory.Factory;
import IO.IOinterface;

import java.io.IOException;
import java.util.List;
import Connection.*;
public class RemoveAllByScCommand implements Command {
    private CollectionInterface ci;
    private Factory fac;
    private Validator val = new Validator("Any");
    private String name;
    public RemoveAllByScCommand(CollectionInterface ci, CommandFetch cf, Factory fac){
        cf.addCommand("remove_all_by_screenwriter",this);
        this.ci=ci;
        this.fac=fac;
        val.person();
        name="remove_all_by_screenwriter";
    }
    @Override
    public void execute(IOinterface io, List<String> args) throws IOException, ClassNotFoundException {
        ci.remove_all_by_screenwriter(fac.createPerson(io),io);
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
