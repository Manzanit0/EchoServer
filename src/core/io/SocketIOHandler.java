package core.io;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class SocketIOHandler extends IOHandler {
    public SocketIOHandler(Socket socket) throws IOException {
        super(new PrintStream(socket.getOutputStream(), true), socket.getInputStream());
    }
}