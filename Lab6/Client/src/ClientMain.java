import Connection.*;
import IO.*;
import Factory.*;
import java.io.*;
import java.net.SocketException;
import java.nio.file.NoSuchFileException;
import java.util.Map;

public class ClientMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
            IOinterface iOclient = new IOconsole(System.in, System.out, true);
        try {
            ServerConnect scon = new ServerConnect(iOclient);
            scon.connect("localhost", 2229);
            IOinterface ioServer = new IOconsole(scon.getInputStream(), scon.getOutputStream(), true);
            while (!ioServer.ready()) {
            }
            TransferObject fromServer = (TransferObject) ioServer.readObj();
            Map<String, Connection.Validator> commandMap = (java.util.HashMap<String, Connection.Validator>) fromServer.getObject();
            Factory fac = new IOfactory();
            CommandFetch cf = new CommandFetch(commandMap, ioServer, fac);
            while (true) {
                try {
                    cf.run(iOclient.readLine(), iOclient);
                } catch (StackOverflowError e) {
                    System.out.println("Сломал компудахтер своей рекурсией, ты доволеннн????");
                }
            }
        } catch (NullPointerException| SocketException e){
            iOclient.writeln("Сервер принял ислам, соединения больше не будет");
        }
    }
}
