package Commands;

import IO.IOinterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import Connection.*;
public class HelpCommand implements Command {
    private BufferedReader reader;
    private String help;
    private Validator val = new Validator("Any");
    private String name;
    HelpCommand(CommandFetch cf, String path) throws IOException {
        File myFile = new File(path);
        reader = new BufferedReader(new FileReader(path));
        name="help";
        help="";
        while(reader.ready()) {
            help=help +reader.readLine()+"\n";
        }
        cf.addCommand("help",this);
    }

    @Override
    public void execute(IOinterface io, List<String> args) throws IOException {
        io.write(help);
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
