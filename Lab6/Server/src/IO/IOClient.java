package IO;

import java.io.*;
import java.util.Scanner;

public class IOClient implements IOinterface {
    private Writer output;
    private Reader input;
    private Scanner scan;
    private InputStream in;
    private OutputStream out;
    private boolean interactive;
    public IOClient(InputStream in, OutputStream out,boolean interactive) throws IOException {
        this.interactive = interactive;
        this.in=in;
        this.out = out;
        output= new OutputStreamWriter(out);
        scan = new Scanner(in);
    }
    @Override
    public void write(String str) throws IOException {
        output.write(str);
        output.flush();
    }

    @Override
    public void writeln(String str) throws IOException {
        write(str+"\n");
    }

    @Override
    public String readLine() throws IOException {
        return null;
    }

    @Override
    public boolean hasNextLine() throws IOException {
        return false;
    }

    @Override
    public boolean ready() {
        return false;
    }

    @Override
    public boolean isInteractive() {
        return false;
    }

    @Override
    public void writeObj(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(obj);
        oos.flush();
    }

    @Override
    public Object readObj() throws IOException, ClassNotFoundException {
        return new ObjectInputStream(in).readObject();
    }

    @Override
    public Object read() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
