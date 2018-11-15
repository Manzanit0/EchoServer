package core.server;

import core.io.IOHandler;
import core.io.SocketIOHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private IOHandler consoleIOHandler;
    private SocketIOHandler socketIO;

    public Server(ServerSocket serverSocket, IOHandler consoleIOHandler) {
        this.serverSocket = serverSocket;
        this.consoleIOHandler = consoleIOHandler;
    }

    public void start() throws IOException {
        writeMessage("server is running on port: " + getPort(), consoleIOHandler);

        acceptConnection();
        writeMessage("A new client has connected.", consoleIOHandler);

        handleMessages();
    }

    private void acceptConnection() throws IOException {
        Socket socket = serverSocket.accept();
        socketIO = new SocketIOHandler(socket);
    }

    private void handleMessages() throws IOException {
        String message;
        while((message = readNextMessage()) != null) {
            writeMessage(message, consoleIOHandler);
            writeMessage(message, socketIO);
        }
    }

    public String readNextMessage() throws IOException {
        return socketIO.read();
    }

    private void writeMessage(String message, IOHandler out) {
        out.write(message);
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }
}