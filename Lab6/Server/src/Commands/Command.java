package Commands;
import Connection.Validator;
import IO.IOinterface;

import java.io.IOException;
import java.util.List;

public interface Command {
    void execute(IOinterface io, List<String> args) throws IOException, ClassNotFoundException;
    Validator getValidator();
    String getName();
}
