import Connection.*;
import IO.*;
import Factory.*;

import java.io.*;
import java.util.Map;

/**
 * главный класс клиента
 */
public class ClientMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
            IOinterface iOclient = new IOconsole(System.in, System.out, true);
        try {
            ServerConnect scon = new ServerConnect(iOclient);
            if(args.length==2) {
                try {
                    scon.connect(args[0], Integer.parseInt(args[1]));
                }catch (NumberFormatException e){
                    System.out.println("Неверно указан порт");
                    System.exit(0);
                }
            }
            else{
                System.out.println("Нужно указать хост и порт");
                System.exit(0);
            }
            IOinterface ioServer = new IOconsole(scon.getInputStream(), scon.getOutputStream(), true);
            while (!ioServer.ready()) {
            }
            TransferObject fromServer = (TransferObject) ioServer.readObj();
            Map<String, Connection.Validator> commandMap = (java.util.HashMap<String, Connection.Validator>) fromServer.getObject();
            Factory fac = new IOfactory();
            CommandFetch cf = new CommandFetch(commandMap, ioServer, fac);
            while (true) {
                try {
                    cf.run(iOclient.readLine().trim(), iOclient);
                } catch (StackOverflowError e) {
                    System.out.println("Сломал компудахтер своей рекурсией, ты доволеннн????");
                }
            }
        } catch (IOException|NullPointerException e){
            iOclient.writeln("Сервер принял ислам, соединения больше не будет");
        }
    }
}
