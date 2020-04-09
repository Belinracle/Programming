package Connection;

import Factory.Factory;
import IO.IOconsole;
import IO.IOinterface;

import java.io.*;
import java.util.*;

public class  CommandFetch {
    private Map<String, Validator> commandMap;
    private Thread hook;
    private IOinterface ioServer;
    Factory fac;

    public CommandFetch(Map<String, Validator> commandMap, IOinterface ioServer, Factory fac) {
        this.commandMap = commandMap;
        this.fac = fac;
        this.ioServer = ioServer;
    }

    public void run(String str, IOinterface io) throws IOException {
        try {
            List<String> words = Arrays.asList(str.split(" +"));
            if (words.get(0).equals("exit")) {
                System.exit(0);
            }
            if (commandMap.get(words.get(0).toLowerCase()).validate(words)) {
                if (words.get(0).toLowerCase().equals("execute")) {
                    IOinterface nio = new IOconsole(new FileInputStream(new File(words.get(1))), System.out, false);
                    while (nio.hasNextLine()) {
                        String request = nio.readLine();
                        run(request, nio);
                    }
                }
                else {
                    TransferObject trans = new TransferObject(str);
                    if (words.get(0).toLowerCase().equals("update")) {
                        trans.putObject(fac.updateMovie(words.get(1), io));
                    } else {
                        if (words.get(0).toLowerCase().equals("exit")) {
                            System.exit(0);
                        }
                        if (commandMap.get(words.get(0)).needMovie()) {
                            trans.putObject(fac.createMovie(io, words));
                        }
                        if (commandMap.get(words.get(0)).needPerson()) {
                            trans.putObject(fac.createPerson(io));
                        }
                    }
                    ioServer.writeObj(trans);
                    long start = System.currentTimeMillis();
                    while (!ioServer.ready()) {
                        long finish = System.currentTimeMillis();
                        if(finish-start>1000){
                            throw new IOException();
                        }
                    }
                    while (ioServer.ready()) {
                            io.writeln(ioServer.readLine());
                    }
                }
            } else io.writeln("Неверные аргументы команды");
        } catch (NullPointerException e) {
            System.out.println("Неизвестная команда");
        }catch(IllegalArgumentException e){
            io.writeln("Скрипт составлен неверно");
        }
    }
}