package Commands;

import Collection.CollectionInterface;
import Collection.DeQueCollection;
import Connection.TransferObject;
import Factory.*;
import IO.IOconsole;
import IO.IOinterface;
import IO.IOtrans;
import Parsers.CSVParser;
import Parsers.Parser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import Connection.*;
public class ControlUnit {
    private CommandFetch cf;
    private IOinterface iOinterface;
    public ControlUnit(CommandFetch cf) throws IOException {
        this.cf = cf;
        this.iOinterface = iOinterface;
        CollectionInterface dmc = new DeQueCollection();
        IDFactory id = new IDFactory("idContainer.txt");
        Factory iomov = new TransferObjectFactory();
        Command add = new AddCommand(dmc,iomov, cf);
        Command show = new ShowCommand(dmc, cf);
        Command info = new InfoCommand( dmc,cf);
        Command help = new HelpCommand(cf, "SomeFile.txt");
        Command update = new UpdateCommand(dmc,iomov,cf);
        Parser csvPars = new CSVParser();
        Command save = new SaveCommand( dmc, "Save.txt",cf,csvPars,id);
        Command remFirst = new RemoveFirstCommand(dmc, cf);
        Command removeID = new RemoveCommand(dmc,cf);
        Command clear = new ClearCommand(dmc, cf);
        Command exit = new ExitCommand(cf);
        Command addIfMax = new AddIfMaxCommand(dmc,cf,iomov);
        Command addIfMin = new AddIfMinCommand(dmc,cf,iomov);
        Command removeSc = new RemoveAllByScCommand(dmc,cf,iomov);
        Command coutByMpaaRating = new CountByMpaaCommand(dmc,cf);
        Command printAsc = new PrintAscendingCommand(dmc,cf);
        Command execute = new ExecuteCommand(cf);
        IOinterface ioFile = new IOconsole(new FileInputStream(new File("Save.txt")), System.out, true);
        dmc.load(csvPars,ioFile);
    }
    public void process(String str) throws IOException {
        cf.run(str,iOinterface);
    }
    public Map<String, Validator> getValMap(){
        Map<String,Command> cm = cf.getMap();
        Map<String, Validator> vm = new HashMap<>();
        for(Map.Entry cmd :cm.entrySet()){
            vm.put(cm.get(cmd.getKey()).getName(),cm.get(cmd.getKey()).getValidator());
        }
        return vm;
    }
    public void setCIO(IOinterface io, TransferObject trans) {
        iOinterface = new IOtrans(trans, io);
    }
}