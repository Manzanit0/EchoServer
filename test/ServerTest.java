import mocks.MockServerSocket;
import mocks.MockSocket;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    private ByteArrayOutputStream output;
    private Server server;

    @Before
    public void setup() throws IOException {
        ServerSocket serverSocket = new MockServerSocket(4200);
        output = new ByteArrayOutputStream();
        server = new Server(serverSocket, new PrintStream(output));
    }

    @Test
    public void opensSocketOnSpecifiedPort() {
        assertEquals(true, server.isListening());
        assertEquals(4200, server.getPort());
    }

    @Test
    public void informsOfNewClientConnection() throws IOException {
        server.start();
        assert(output.toString().contains("A new client has connected."));
    }

    @Test
    public void receivesMessagesFromClient() throws IOException {
        ByteArrayOutputStream clientOutput = new ByteArrayOutputStream();
        ByteArrayInputStream clientInput = new ByteArrayInputStream("Hello world!".getBytes());
        Socket clientSocket = new MockSocket(clientInput, clientOutput);

        ServerSocket serverSocket = new MockServerSocket(clientSocket);
        server = new Server(serverSocket, new PrintStream(output));

        server.start();

        assertEquals("Hello world!", server.getClientMessage());
    }

}
