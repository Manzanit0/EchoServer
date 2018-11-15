import core.server.Server;
import mocks.ServerSocketMock;
import mocks.SocketMock;
import mocks.StandardIOHandlerMock;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ServerTest {
    private ByteArrayOutputStream serverConsoleOutput;
    private Server server;

    @Before
    public void setup() throws IOException {
        ServerSocketMock serverSocket = new ServerSocketMock(4200);
        serverSocket.setSocket(new SocketMock()); // Socket to be returned after a connection is accepted.

        serverConsoleOutput = new ByteArrayOutputStream();
        StandardIOHandlerMock serverIoHandler = StandardIOHandlerMock.createMock(serverConsoleOutput);

        server = new Server(serverSocket, serverIoHandler);
    }

    @Test
    public void acceptsIncomingConnection() throws IOException {
        server.start();

        assertNotNull(server.getConnectionSocket());
    }

    @Test
    public void informsOfNewClientConnection() throws IOException {
        server.start();

        assertTrue(serverConsoleOutput.toString().contains("A new client has connected."));
    }

    @Test
    public void printsMessagesWhileConnexionIsOpen() throws IOException {
        SocketMock socket = createSocket("One\nTwo\nThree");
        ServerSocketMock serverSocket = createServerSocket(socket);
        StandardIOHandlerMock serverIoHandler = StandardIOHandlerMock.createMock(serverConsoleOutput);

        server = new Server(serverSocket, serverIoHandler);

        server.start();

        assertTrue(serverConsoleOutput.toString().contains("One\nTwo\nThree"));
    }

    private ServerSocketMock createServerSocket(SocketMock socket) throws IOException {
        ServerSocketMock serverSocket = new ServerSocketMock(4200);
        serverSocket.setSocket(socket);
        return serverSocket;
    }

    private SocketMock createSocket(String socketInput) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayInputStream input = new ByteArrayInputStream(socketInput.getBytes());
        return new SocketMock(input, output);
    }
}
