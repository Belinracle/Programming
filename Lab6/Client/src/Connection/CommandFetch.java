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
        hook = new Thread(() -> System.out.println("Работа программы прервана. Изменения не сохранены"));
        Runtime.getRuntime().addShutdownHook(hook);
    }

    public void run(String str, IOinterface io) throws IOException {
        try {
            List<String> words = Arrays.asList(str.split(" +"));
            if (words.get(0).equals("exit")) {
                Runtime.getRuntime().removeShutdownHook(hook);
            }
            if (commandMap.get(words.get(0).toLowerCase()).validate(words)) {
                TransferObject trans = new TransferObject(str);
                if (words.get(0).toLowerCase().equals("execute")) {
                    IOinterface nio = new IOconsole(new FileInputStream(new File(words.get(1))), System.out, false);
                    while (nio.hasNextLine()) {
                        String request = nio.readLine();
                        if (!str.isEmpty()) {
                            run(request, nio);
                        }
                    }
                }
                if (words.get(0).toLowerCase().equals("update")) {
                    trans.putObject(fac.updateMovie(words.get(1), io));
                } else {
                    if (commandMap.get(words.get(0)).needMovie()) {
                        trans.putObject(fac.createMovie(io, words));
                    }
                    if (commandMap.get(words.get(0)).needPerson()) {
                        trans.putObject(fac.createPerson(io));
                    }
                }
                ioServer.writeObj(trans);
                while (!ioServer.hasNextLine()) {
                }
                StringBuilder sb = new StringBuilder();
                while (ioServer.hasNextLine()) {
                    sb.append(ioServer.readLine()).append("\n");
                }
                io.writeln(sb.toString());
            } else io.write("Неверные аргументы команды");
        } catch (NoSuchElementException | IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Неизвестная команда");
        }catch(IOException|IllegalArgumentException e){
            io.writeln("Скрипт составлен неверно");
        }
    }
}