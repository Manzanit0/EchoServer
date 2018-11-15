package core.server;

import core.io.IOHandler;
import core.io.SocketIOHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private IOHandler serverConsoleIO;
    private SocketIOHandler socketIO;

    public Server(ServerSocket serverSocket, IOHandler serverConsoleIO) {
        this.serverSocket = serverSocket;
        this.serverConsoleIO = serverConsoleIO;
    }

    public void start() throws IOException {
        publishMessage("server is running on port: " + getPort(), serverConsoleIO);

        acceptIncomingConnection();
        publishMessage("A new client has connected.", serverConsoleIO);

        receiveClientMessages();
    }

    private void acceptIncomingConnection() throws IOException {
        Socket socket = serverSocket.accept();
        socketIO = new SocketIOHandler(socket);
    }

    private void receiveClientMessages() throws IOException {
        String message;
        while((message = getClientMessage()) != null) {
            publishMessage(message, serverConsoleIO);
            publishMessage(message, socketIO);
        }
    }

    private void publishMessage(String message, IOHandler out) {
        out.write(message);
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    public String getClientMessage() throws IOException {
        return socketIO.read();
    }
}