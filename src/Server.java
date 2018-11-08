import java.io.PrintStream;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private ServerSocket socket;

    private PrintStream out;

    public Server(ServerSocket socket, PrintStream out) {
        this.socket = socket;
        this.out = out;
    }

    public void start() throws IOException {
        socket.accept();
        publishNewClientConnection();
    }

    public boolean isListening() {
        return !socket.isClosed();
    }

    public int getPort() {
        return socket.getLocalPort();
    }

    public void publishNewClientConnection() {
        out.println("A new client has connected.");
    }
}