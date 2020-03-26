package Commands;

import IO.IOinterface;

import java.io.IOException;
import java.util.*;

public class  CommandFetch {
    private Map<String,Command> commandMap;
    private Thread hook;
    public CommandFetch()  {
        commandMap = new HashMap<>();
        hook = new Thread(() -> System.out.println("Работа программы прервана. Изменения не сохранены"));
        Runtime.getRuntime().addShutdownHook(hook);
    }

    void run(String str, IOinterface io) throws IOException {
        try {
            ArrayList<String> words = new ArrayList<>(Arrays.asList(str.split(" ")));
            words.removeAll(Collections.singleton(""));
            if(words.get(0).equals("exit")){
                Runtime.getRuntime().removeShutdownHook(hook);
            }
            commandMap.get(words.get(0).toLowerCase()).execute(io,words);
        } catch (NoSuchElementException | IndexOutOfBoundsException | ClassNotFoundException e) {
            System.out.println("Неизвестная команда");
        }
    }

    /**
     * метод для добавления команды в HashMap
     * @param str название команды
     * @param cmd экземпляр команды
     */
    public void addCommand(String str,Command cmd){
        commandMap.put(str,cmd);
    }
    public Map<String,Command> getMap(){
        return commandMap;
    }
}
