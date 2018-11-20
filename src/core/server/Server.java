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
    private Socket connectionSocket;

    public Server(ServerSocket serverSocket, IOHandler consoleIOHandler) {
        this.serverSocket = serverSocket;
        this.consoleIOHandler = consoleIOHandler;
    }

    public void start() throws IOException {
        write("server is running on port: " + getPort());

        acceptConnection();
        write("A new client has connected.");

        handleMessages();
    }

    private void acceptConnection() throws IOException {
        connectionSocket = serverSocket.accept();
        socketIO = new SocketIOHandler(connectionSocket);
    }

    private void handleMessages() throws IOException {
        String message;
        while((message = receive()) != null) {
            write(message);
            send(message);
        }
    }

    private void write(String message) {
        consoleIOHandler.write(message);
    }

    private void send(String message) {
        socketIO.write(message);
    }

    private String receive() throws IOException {
        return socketIO.read();
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    public Socket getConnectionSocket() {
        return connectionSocket;
    }
}