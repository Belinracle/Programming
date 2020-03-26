package IO;

import java.io.*;
import java.util.Scanner;

public class IOconsole implements  IOinterface {
    private Writer output;
    private Reader input;
    private Scanner scan;
    private ObjectInputStream oin;
    private ObjectOutputStream oout;
    private boolean interactive;
    public IOconsole(InputStream in, OutputStream out,boolean interactive) throws IOException {
        this.interactive = interactive;
        oout = new ObjectOutputStream(out);
        oin = new ObjectInputStream(in);
        output= new OutputStreamWriter(out);
        scan = new Scanner(in);
    }
    @Override
    public void write(String str){
        try {
            output.write(str);
            output.flush();
        }catch (IOException e){
            write(e.getMessage());
        }
    }

    @Override
    public void writeln(String str){
        write(str+"\n");
    }

    @Override
    public String readLine() {
        return scan.nextLine();
    }

    @Override
    public boolean hasNextLine(){
        return scan.hasNextLine();
    }

    @Override
    public boolean ready() {
        return scan.hasNext();
    }

    @Override
    public boolean isInteractive() {
        return interactive ;
    }

    @Override
    public void writeObj(Object obj) throws IOException {
        oout.writeObject(obj);
    }

    @Override
    public Object readObj() throws IOException, ClassNotFoundException {
        return oin.readObject();
    }

    @Override
    public Object read() {
        return scan.next();
    }

    @Override
    public void close() throws IOException {
        output.close();
    }
}
