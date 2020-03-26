package Commands;


import Collection.CollectionInterface;
import IO.IOinterface;

import java.io.IOException;
import java.util.List;
import Connection.*;
public class ShowCommand implements Command {
    private CollectionInterface coll;
    private Validator val = new Validator("Any");
    private String name;
    ShowCommand(CollectionInterface coll,CommandFetch cf){
        this.coll=coll;
        cf.addCommand("show",this);
        name="show";
    }
    @Override
    public void execute(IOinterface io, List<String> args) throws IOException {
        io.write("Состояние коллекции: \n"+coll.show());
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
