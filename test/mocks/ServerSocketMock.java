package mocks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketMock extends ServerSocket {
    private final int portNumber;
    private Socket socket;

    public ServerSocketMock(int portNumber) throws IOException {
        this.portNumber = portNumber;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public int getLocalPort() {
        return this.portNumber;
    }

    @Override
    public Socket accept() {
        return socket;
    }
}