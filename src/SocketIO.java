import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketIO {
    private final Socket socket;
    private final BufferedReader socketReader;

    public SocketIO(Socket socket) throws IOException {
        this.socket = socket;
        socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String read() throws IOException {
        return socketReader.readLine();
    }
}
