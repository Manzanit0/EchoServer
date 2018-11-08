import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private ServerSocket socket;

    public void start(int portNumber) throws IOException {
        socket = new ServerSocket(portNumber);
    }

    public boolean isListening() {
        return !socket.isClosed();
    }

    public int getPort() {
        return socket.getLocalPort();
    }
}