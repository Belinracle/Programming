package IO;

import java.io.IOException;

/**
 *Интерфейс для записи/чтения
 */
public interface IOinterface {
    void write(String str) throws IOException;
    void writeln(String str) throws IOException;
    String readLine() throws IOException;
    boolean hasNextLine() throws IOException;
    boolean ready();
    boolean isInteractive();
    void writeObj(Object obj) throws IOException;
    Object readObj() throws IOException, ClassNotFoundException;
    Object read();
    void close() throws IOException;
}
