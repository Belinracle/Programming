package Factory;

import java.io.*;

public class IDFactory {
    private static Integer counter;
    private BufferedReader reader;
    private String path;
    public IDFactory(String path) throws IOException {
        this.path=path;
        reader = new BufferedReader(new FileReader(path));
        counter= Integer.parseInt(reader.readLine());
    }

    /**
     * метод для получения ID и увеличивания счетчика
     */

    static int createID(){
        return ++counter;
    }

    /**
     * метод для получения текущего ID
     * используется при сохранении коллекции
     */
    public static int getCurrentID(){
        return counter;
    }

    /**
     * метод для записи текущего значения ID в файл для хранения
     * @param id
     * @throws FileNotFoundException
     */
    public void write(String id) throws FileNotFoundException {
        PrintWriter writerID = new PrintWriter(path);
        writerID.write(id);
        writerID.close();
    }
}
