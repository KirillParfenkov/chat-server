package by.parf.server;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;

/**
 * Created by parf on 8.9.16.
 */
public class ChatServer implements Runnable {

    /**
     * Default port
     */
    private static final int DEFAULT_PORT = 5555;
    /**
     * Default host
     */
    private static final String DEFAULT_HOST = "localhost";
    /**
     * Default read buffer  = 1 MB
     */
    private static final int DEFAULT_READ_BUFFER = 1024 * 1024;

    /**
     * Buffer for reading from channels
     */
    private ByteBuffer readBuffer = ByteBuffer.allocate(DEFAULT_READ_BUFFER);
    private InetAddress hostAddress;
    private int port;
    private ServerSocketChannel serverChanel;
    private Selector selector;

    public ChatServer(InetAddress address, int port, int readBuffer) throws IOException {
        this.port = port;
        this.hostAddress = address;
        this.readBuffer = ByteBuffer.allocate(readBuffer);
        initSelector();
    }

    public ChatServer(InetAddress address, int port) throws IOException {
        this.port = port;
        this.hostAddress = address;
        this.readBuffer = ByteBuffer.allocate(DEFAULT_READ_BUFFER);
        initSelector();
    }


    public ChatServer() throws IOException {
        this.port = DEFAULT_PORT;
        this.hostAddress = null;
        this.readBuffer = ByteBuffer.allocate(DEFAULT_READ_BUFFER);
        initSelector();
    }

    private void initSelector() throws IOException {

        serverChanel = ServerSocketChannel.open();
        serverChanel.configureBlocking(false);
        serverChanel.socket().bind(new InetSocketAddress(this.hostAddress, port));

        selector = SelectorProvider.provider().openSelector();
        serverChanel.register(selector, SelectionKey.OP_ACCEPT);

    }

    @Override
    public void run() {
        try {
            while(true) {

                int readyChannels = selector.select();
                if (readyChannels == 0) {
                    continue;
                }

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();
                    if (!key.isValid()) {
                        continue;
                    }

                    if (key.isAcceptable()) {
                        accept(key);
                    } else if(key.isReadable()) {
                        read(key);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
            // TODO cancel for selector
        }
    }

    protected void accept(SelectionKey key) throws IOException {

        ServerSocketChannel serverChanel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChanel.accept();
        Socket socket = clientChannel.socket();
        if (clientChannel == null) {
            throw new IOException("Unknown error"); // TODO normal error processing
        }

        clientChannel.configureBlocking(false);
        clientChannel.register(this.selector, SelectionKey.OP_READ);

    }

    protected void read(SelectionKey key) throws IOException {

        SocketChannel channel = (SocketChannel) key.channel();
        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        this.readBuffer.clear();

        int dataSize;
        byte[] data;
        StringBuilder readLine = new StringBuilder();
        while (true) {

            dataSize = channel.read(readBuffer);
            if (dataSize == -1){
                key.cancel();
                break;
            }

            readBuffer.flip();
            CharBuffer charBuff = decoder.decode(readBuffer);

            readLine.append(charBuff);
            this.readBuffer.clear();
        }

        System.out.println(readLine.toString());
    }
}
