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
    private BufferedReader socketReader;

    public Server(ServerSocket socket, PrintStream out) {
        this.socket = socket;
        this.out = out;
    }

    public void start() throws IOException {
        out.println("Server is running on port: " + getPort());

        clientSocket = socket.accept();
        publishNewClientConnection();

        socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String message;
        while((message = getClientMessage()) != null) {
            out.println(message);
        }
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
        return socketReader.readLine();
    }
}