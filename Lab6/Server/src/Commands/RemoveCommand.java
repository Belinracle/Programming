package Commands;

import Collection.CollectionInterface;
import IO.IOinterface;

import java.io.IOException;
import java.util.List;
import Connection.*;
public class RemoveCommand implements Command {
    private CollectionInterface ci;
    private Validator val = new Validator("Int");
    private String name;
    RemoveCommand(CollectionInterface coll, CommandFetch cf){
        cf.addCommand("remove_by_id",this);
        ci=coll;
        name="remove";
    }
    @Override
    public void execute(IOinterface io, List<String> args) throws IOException {
        if (val.validate(args)){
            ci.remove_by_id(Integer.parseInt(args.get(1)),io);
        }
        else io.writeln("Неверные аргуметы команды");
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
