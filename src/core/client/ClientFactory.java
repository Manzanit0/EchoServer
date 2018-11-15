package core.client;

import core.io.IOHandler;
import core.io.StandardIOHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientFactory {
    public static Client create(int port) throws IOException {
        Socket socket = new Socket(InetAddress.getByName("localhost"), port);
        IOHandler io = new StandardIOHandler();

        return new Client(socket, io);
    }
}
