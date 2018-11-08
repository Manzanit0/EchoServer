package mocks;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Socket;

public class MockSocket extends Socket {
    private final ByteArrayOutputStream output;
    private final ByteArrayInputStream input;

    public MockSocket() {
        output = new ByteArrayOutputStream();
        input = new ByteArrayInputStream("mock value".getBytes());
    }

    public MockSocket(ByteArrayInputStream input, ByteArrayOutputStream output) {
        this.output = output;
        this.input = input;
    }

    @Override
    public ByteArrayOutputStream getOutputStream() {
        return output;
    }

    @Override
    public ByteArrayInputStream getInputStream() {
        return input;
    }
}