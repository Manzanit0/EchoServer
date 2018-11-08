package mocks;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Socket;

public class SocketMock extends Socket {
    private final ByteArrayOutputStream output;
    private final ByteArrayInputStream input;

    public SocketMock() {
        output = new ByteArrayOutputStream();
        input = new ByteArrayInputStream("mock value".getBytes());
    }

    public SocketMock(ByteArrayInputStream input, ByteArrayOutputStream output) {
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