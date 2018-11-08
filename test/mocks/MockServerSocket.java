package mocks;

import java.io.IOException;
import java.net.ServerSocket;

public class MockServerSocket extends ServerSocket {
    private final int portNumber;

    public MockServerSocket(int portNumber) throws IOException {
        this.portNumber = portNumber;
    }

    @Override
    public int getLocalPort() {
        return this.portNumber;
    }

    @Override
    public MockSocket accept() {
        return new MockSocket();
    }
}