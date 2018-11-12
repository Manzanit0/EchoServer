package core.client;

import core.common.ConsoleIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientFactory {
    public static Client create(int port) throws IOException {
        Socket socket = new Socket(InetAddress.getByName("localhost"), port);
        ConsoleIO io = new ConsoleIO(System.out, System.in);

        return new Client(socket, io);
    }
}
