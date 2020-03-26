package IO;

import Connection.TransferObject;

import java.io.IOException;

public class IOtrans implements IOinterface {
    TransferObject trans;
    IOinterface io;
    public IOtrans(TransferObject trans, IOinterface io){
        this.io=io;
        this.trans=trans;
    }
    @Override
    public void write(String str) throws IOException {
        io.write(str);
    }

    @Override
    public void writeln(String str) throws IOException {
        io.writeln(str);
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

    }

    @Override
    public Object readObj() throws IOException, ClassNotFoundException {
        return trans;
    }

    @Override
    public Object read() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
