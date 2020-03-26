package Commands;

import Collection.CollectionInterface;
import Factory.Factory;
import IO.IOinterface;

import java.io.IOException;
import java.util.List;
import Connection.*;
public class UpdateCommand implements Command {
    private CollectionInterface ci;
    private Factory movFac;
    private Validator val = new Validator("Int");
    private String name;
    UpdateCommand(CollectionInterface ci,Factory fac,CommandFetch cf){
        cf.addCommand("update", this);
        this.ci = ci;
        name="update";
        movFac= fac;
        val.movie();
    }
    @Override
    public void execute(IOinterface io, List<String> args) throws IOException, ClassNotFoundException {
        if(val.validate(args)) {
            ci.update(Integer.parseInt(args.get(1)),movFac.updateMovie(args.get(1),io),io);
        }
        else io.writeln("Неверные аргументы команды");
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
