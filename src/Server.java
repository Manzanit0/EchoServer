import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket socket;
    private Socket clientSocket;

    private ConsoleIO out;
    private SocketIO socketIO;

    public Server(ServerSocket socket, ConsoleIO out) {
        this.socket = socket;
        this.out = out;
    }

    public void start() throws IOException {
        out.write("Server is running on port: " + getPort());

        clientSocket = socket.accept();
        socketIO = new SocketIO(clientSocket);
        publishNewClientConnection();

        String message;
        while((message = getClientMessage()) != null) {
            out.write(message);
        }
    }

    public int getPort() {
        return socket.getLocalPort();
    }

    public void publishNewClientConnection() {
        out.write("A new client has connected.");
    }

    public String getClientMessage() throws IOException {
        return socketIO.read();
    }
}