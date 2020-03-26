package IO;

import java.io.*;
import java.util.Scanner;

public class IOconsole implements  IOinterface {
    private Writer output;
    private Reader input;
    private Scanner scan;
    private InputStream in;
    private OutputStream out;
    private boolean interactive;
    private BufferedReader bin;
    public IOconsole(InputStream in, OutputStream out,boolean interactive) throws IOException {
        this.interactive = interactive;
        this.in=in;
        this.out = out;
        output= new OutputStreamWriter(out);
        scan = new Scanner(in);
        bin = new BufferedReader(new InputStreamReader(in));
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
        return scan.hasNext();
    }

    @Override
    public boolean hasNext() {
        return scan.hasNext();
    }

    @Override
    public boolean ready() throws IOException {
        return bin.ready();
    }

    @Override
    public boolean isInteractive() {
        return interactive ;
    }

    @Override
    public void writeObj(Object obj) throws IOException {
        new ObjectOutputStream(out).writeObject(obj);
    }

    @Override
    public Object readObj() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        Object obj =ois.readObject();
        return  obj;
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
