package Connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Класс для обработки новых соединений клиентов
 */
public class ClientHandler {
    ServerSocketChannel ssch;
    Selector selector;
    SocketChannel channel;
    public ClientHandler(int port) throws IOException {
        ssch=ServerSocketChannel.open();
        ssch.configureBlocking(false);
        ssch.socket().bind(new InetSocketAddress(port));
        selector = Selector.open();
        ssch.register(selector, SelectionKey.OP_ACCEPT);
    }
    public Selector getSelector(){
        return selector;
    }
    public void acceptConnect() throws IOException {
        channel = ssch.accept();
        channel.configureBlocking(false);
        channel.register(selector,SelectionKey.OP_WRITE);
    }
    public SocketChannel getSocketChan(){
        return channel;
    }
}
