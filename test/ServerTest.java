import mocks.MockServerSocket;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    @Test
    public void opensSocketOnSpecifiedPort() throws IOException {
        ServerSocket serverSocket = new MockServerSocket(4200);
        Server server = new Server(serverSocket, new PrintStream(new ByteArrayOutputStream()));


        assertEquals(true, server.isListening());
        assertEquals(4200, server.getPort());
    }

    @Test
    public void informsOfNewClientConnection() throws IOException {
        ServerSocket serverSocket = new MockServerSocket(4200);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Server server = new Server(serverSocket, new PrintStream(output));

        server.start();

        assert(output.toString().contains("A new client has connected."));
    }
}
