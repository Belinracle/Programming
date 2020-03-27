package IO;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class IOClient implements IOinterface {
    private boolean interactive;
    SocketChannel socCh;
    public IOClient(SocketChannel socCh, boolean interactive) throws IOException {
        this.interactive = interactive;
        this.socCh=socCh;
    }
    @Override
    public void write(String str) throws IOException {
        ByteBuffer bb = ByteBuffer.wrap(str.getBytes());
        socCh.write(bb);
        System.out.println(str);
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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        socCh.write(ByteBuffer.wrap(baos.toByteArray()));
    }

    @Override
    public Object readObj() throws IOException, ClassNotFoundException {
        ByteBuffer bb = ByteBuffer.allocate(5 * 1024);
        try{
        socCh.read(bb);
        return new ObjectInputStream(new ByteArrayInputStream(bb.array())).readObject();
        }catch(IOException e){
            socCh.close();
            throw new ConnectException("Соединение с клиентом разорвано");
        }
    }

    @Override
    public Object read() {
        return null;
    }

    @Override
    public void close() throws IOException {
    }
}
