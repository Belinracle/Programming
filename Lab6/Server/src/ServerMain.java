import Commands.CommandFetch;
import Commands.ControlUnit;
import Connection.TransferObject;
import IO.IOClient;
import IO.IOconsole;
import IO.IOinterface;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket ssoc=new ServerSocket(7890);
        CommandFetch cf = new CommandFetch();
        ControlUnit cu = new ControlUnit(cf);
        Socket soc=ssoc.accept();
        System.out.println("cnct");
        try {
            IOinterface ioClient = new IOClient(soc.getInputStream(), soc.getOutputStream(), true);
            TransferObject mapTOClient=new TransferObject("map");
            mapTOClient.putObject(cu.getValMap());
            ioClient.writeObj(mapTOClient);
            System.out.println("nice");
            while (true) {
                TransferObject trans = (TransferObject) ioClient.readObj();
                System.out.println(trans.getRequest());
                cu.setCIO(ioClient, trans);
                cu.process(trans.getRequest());
            }
        }catch(SocketException e){
            System.out.println("Клиент отключился");
        }
    }
}
