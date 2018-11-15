package core.server;

import core.io.IOHandler;
import core.io.StandardIOHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerFactory {
    public static Server create(int port) throws IOException {
        ServerSocket socket = new ServerSocket(port);
        IOHandler io = new StandardIOHandler();

        return new Server(socket, io);
    }
}
