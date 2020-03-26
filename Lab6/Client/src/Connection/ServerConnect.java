package Connection;

import IO.IOinterface;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class ServerConnect {
    Socket soc;
    IOinterface ioclient;
    public ServerConnect(IOinterface io){
        ioclient=io;
    }
    public Socket connect(String host,Integer port) throws IOException {
        try {
            soc = new Socket(host,port);
            ioclient.writeln("Соединение успешно установлено");
            return soc;
        }catch (ConnectException e){
            ioclient.write("Не удается установить соединение с сервером, возможно, указаны неверные хост и порт");
            return null;
        }
    }
    public InputStream getInputStream() throws IOException {
        return soc.getInputStream();
    }
    public OutputStream getOutputStream() throws IOException{
        return soc.getOutputStream();
    }
    public void close() throws IOException {
        soc.close();
    }
}
