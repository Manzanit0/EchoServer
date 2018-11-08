package mocks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServerSocket extends ServerSocket {
    private final int portNumber;
    private final Socket clientSocket;

    public MockServerSocket(int portNumber) throws IOException {
        this.portNumber = portNumber;
        clientSocket = new MockSocket();
    }

    public MockServerSocket(Socket clientSocket) throws IOException {
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