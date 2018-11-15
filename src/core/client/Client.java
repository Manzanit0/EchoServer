package core.client;

import core.io.IOHandler;
import core.io.SocketIOHandler;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private final SocketIOHandler socketIoHandler;
    private final IOHandler ioHandler;

    public Client(Socket clientSocket, IOHandler ioHandler) throws IOException {
        this.ioHandler = ioHandler;
        socketIoHandler = new SocketIOHandler(clientSocket);
    }

    public void start() throws IOException {
        String input;
        while ((input = ioHandler.read()) != null) {
            socketIoHandler.write(input);
            String response = socketIoHandler.read();
            ioHandler.write(response);
        }
    }
}
