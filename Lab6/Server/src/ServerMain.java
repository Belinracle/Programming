import Commands.CommandFetch;
import Commands.ControlUnit;
import Connection.ClientHandler;
import Connection.TransferObject;
import IO.IOClient;
import IO.IOinterface;

import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class ServerMain {
    public static boolean sent;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CommandFetch cf = new CommandFetch();
        ClientHandler clientHandler = new ClientHandler(2229);
        ControlUnit cu = new ControlUnit(cf);
        TransferObject mapTOClient = new TransferObject("map");
        mapTOClient.putObject(cu.getValMap());
        TransferObject trans = null;
        IOinterface ioClient = null;
        String str = "";
        while (true) {
            clientHandler.getSelector().select();
            Iterator iter = clientHandler.getSelector().selectedKeys().iterator();
            while (iter.hasNext()) {
                    SelectionKey selKey = (SelectionKey) iter.next();
                    iter.remove();
                try {
                    if (!selKey.isValid())
                    {
                        System.out.println("dada");
                        continue;
                    }
                    if (selKey.isAcceptable()) {
                        clientHandler.acceptConnect();
                        sent=false;
                    }
                    if (selKey.isWritable()) {
                        if (!sent) {
                            ioClient = new IOClient((SocketChannel) selKey.channel(), true);
                            ioClient.writeObj(mapTOClient);
                            sent=true;
                        } else {
                            cu.setCIO(ioClient, trans);
                            cu.process(str);
                        }
                        selKey.interestOps(SelectionKey.OP_READ);
                    }
                    if (selKey.isReadable()) {
                        ioClient = new IOClient((SocketChannel) selKey.channel(), true);
                        trans = (TransferObject) ioClient.readObj();
                        str = trans.getRequest();
                        selKey.interestOps(SelectionKey.OP_WRITE);
                    }
                } catch (ConnectException  e) {
                    System.out.println(e.getMessage());
                    sent=false;
                }
            }
        }
    }
}
