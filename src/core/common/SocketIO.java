package core.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketIO {
    private final BufferedReader socketReader;
    private final PrintWriter socketWriter;

    public SocketIO(Socket socket) throws IOException {
        socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketWriter = new PrintWriter(socket.getOutputStream(), true);
    }

    public String read() throws IOException {
        return socketReader.readLine();
    }

    public void write(String message) {
        socketWriter.println(message);
    }
}
