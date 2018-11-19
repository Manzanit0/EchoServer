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
        while ((input = read()) != null) {
            handleInput(input);
        }
    }

    private void handleInput(String input) throws IOException {
        sendMessage(input);
        handleServerResponse();
    }

    private void handleServerResponse() throws IOException {
        String response = readMessage();
        write(response);
    }

    private void write(String response) {
        ioHandler.write(response);
    }

    private String read() throws IOException {
        return ioHandler.read();
    }

    private void sendMessage(String input) {
        socketIoHandler.write(input);
    }

    private String readMessage() throws IOException {
        return socketIoHandler.read();
    }
}
