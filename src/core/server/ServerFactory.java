package core.server;

import core.common.ConsoleIO;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerFactory {
    public static Server create(int port) throws IOException {
        ServerSocket socket = new ServerSocket(port);
        ConsoleIO io = new ConsoleIO(System.out, System.in);

        return new Server(socket, io);
    }
}
