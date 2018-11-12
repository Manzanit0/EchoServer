import core.common.ConsoleIO;
import core.server.Server;
import mocks.ServerSocketMock;
import mocks.SocketMock;
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
        ServerSocket serverSocket = new ServerSocketMock(4200);
        output = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(output);
        server = new Server(serverSocket, new ConsoleIO(ps, System.in));
    }

    @Test
    public void opensSocketOnSpecifiedPort() {
        assertEquals(4200, server.getPort());
    }

    @Test
    public void informsOfNewClientConnection() throws IOException {
        server.start();
        assert(output.toString().contains("A new client has connected."));
    }

    @Test
    public void printsMessagesWhileConnexionIsOpen() throws IOException {
        ByteArrayOutputStream clientOutput = new ByteArrayOutputStream();
        ByteArrayInputStream clientInput = new ByteArrayInputStream("One\nTwo\nThree".getBytes());
        Socket clientSocket = new SocketMock(clientInput, clientOutput);

        ServerSocket serverSocket = new ServerSocketMock(clientSocket);
        PrintStream ps = new PrintStream(output);
        server = new Server(serverSocket, new ConsoleIO(ps, System.in));

        server.start();

        assert(output.toString().contains("One\nTwo\nThree"));
    }
}
