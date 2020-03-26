import Connection.*;
import IO.*;
import Factory.*;
import java.io.*;
import java.util.Map;

public class ClientMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        IOinterface iOclient = new IOconsole(System.in,System.out,true);
        ServerConnect scon = new ServerConnect(iOclient);
        scon.connect("localhost",7890);
        IOinterface ioServer = new IOconsole(scon.getInputStream(),scon.getOutputStream(),true);
        while (!ioServer.ready()){}
        Map<String, Validator> commandMap = (java.util.HashMap<String,Validator>)ioServer.readObj();
        Factory fac = new IOfactory();
        CommandFetch cf = new CommandFetch(commandMap,ioServer,fac);
        while(true) {
            try {
                cf.run(iOclient.readLine(),iOclient);
            }catch (StackOverflowError e){
                System.out.println("Сломал компудахтер своей рекурсией, ты доволеннн????");
            }
        }
    }
}
