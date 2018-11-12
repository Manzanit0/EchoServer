package core.client;

import core.common.ConsoleIO;
import core.common.SocketIO;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private final SocketIO socketIO;
    private final ConsoleIO consoleIO;

    public Client(Socket clientSocket, ConsoleIO consoleIO) throws IOException {
        this.consoleIO = consoleIO;
        socketIO = new SocketIO(clientSocket);
    }

    public void start() throws IOException {
        String input;
        while ((input = consoleIO.read()) != null) {
            socketIO.write(input);
            String response = socketIO.read();
            consoleIO.write(response);
        }
    }
}
