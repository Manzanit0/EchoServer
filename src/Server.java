import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket socket;
    private Socket clientSocket;

    private PrintStream out;

    public Server(ServerSocket socket, PrintStream out) {
        this.socket = socket;
        this.out = out;
    }

    public void start() throws IOException {
        clientSocket = socket.accept();
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

    public String getClientMessage() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return br.readLine();
    }
}