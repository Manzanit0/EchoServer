package core.server;

import core.io.IOHandler;
import core.io.SocketIOHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private IOHandler out;
    private SocketIOHandler socketIO;

    public Server(ServerSocket serverSocket, IOHandler out) {
        this.serverSocket = serverSocket;
        this.out = out;
    }

    public void start() throws IOException {
        out.write("server is running on port: " + getPort());

        Socket socket = serverSocket.accept();
        socketIO = new SocketIOHandler(socket);
        publishNewClientConnection();

        String message;
        while((message = getClientMessage()) != null) {
            out.write(message);
            socketIO.write(message);
        }
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    public void publishNewClientConnection() {
        out.write("A new client has connected.");
    }

    public String getClientMessage() throws IOException {
        return socketIO.read();
    }
}