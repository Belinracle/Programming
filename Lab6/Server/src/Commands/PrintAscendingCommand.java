package Commands;

import Collection.CollectionInterface;
import IO.IOinterface;

import java.io.IOException;
import java.util.List;
import Connection.*;
public class PrintAscendingCommand implements Command {
    private Validator val = new Validator("Any");
    private CollectionInterface ci;
    private String name;
    PrintAscendingCommand(CollectionInterface ci, CommandFetch cf){
        cf.addCommand("print_ascending",this);
        this.ci=ci;
        name="print_ascending";
    }
    @Override
    public void execute(IOinterface io, List<String> args) throws IOException {
        io.writeln("Элементы коллекции в порядке убывания имени \n" +ci.print_ascending());
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
