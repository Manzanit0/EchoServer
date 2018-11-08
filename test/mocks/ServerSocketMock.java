package mocks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketMock extends ServerSocket {
    private final int portNumber;
    private final Socket clientSocket;

    public ServerSocketMock(int portNumber) throws IOException {
        this.portNumber = portNumber;
        clientSocket = new SocketMock();
    }

    public ServerSocketMock(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        portNumber = 5000;
    }

    @Override
    public int getLocalPort() {
        return this.portNumber;
    }

    @Override
    public Socket accept() {
        return clientSocket;
    }
}