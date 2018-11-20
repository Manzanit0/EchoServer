package core.client;

import core.io.IOHandler;
import core.io.SocketIOHandler;

import java.io.IOException;

public class Client {
    private final SocketIOHandler socketHandler;
    private final IOHandler ioHandler;

    public Client(SocketIOHandler socketHandler, IOHandler ioHandler) {
        this.ioHandler = ioHandler;
        this.socketHandler = socketHandler;
    }

    public void start() throws IOException {
        String input;
        while ((input = read()) != null) {
            handleInput(input);
        }
    }

    private void handleInput(String input) throws IOException {
        send(input);
        String response = receive();
        write(response);
    }

    private void write(String response) {
        ioHandler.write(response);
    }

    private String read() throws IOException {
        return ioHandler.read();
    }

    private void send(String input) {
        socketHandler.write(input);
    }

    private String receive() throws IOException {
        return socketHandler.read();
    }
}
