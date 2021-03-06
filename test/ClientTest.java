import core.client.Client;
import core.io.IOHandler;
import core.io.SocketIOHandler;
import mocks.SocketMock;
import mocks.StandardIOHandlerMock;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    @Test
    public void clientEchosToConsole() throws IOException {
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        IOHandler IOHandler = StandardIOHandlerMock.createMock("One\nTwo", consoleOutput);

        ByteArrayOutputStream clientSocketOutput = new ByteArrayOutputStream();
        ByteArrayInputStream clientSocketInput = new ByteArrayInputStream("One\nTwo".getBytes());
        Socket clientSocket = new SocketMock(clientSocketInput, clientSocketOutput);
        SocketIOHandler socketHandler = new SocketIOHandler(clientSocket);

        Client client = new Client(socketHandler, IOHandler);
        client.start();

        assertEquals("One\nTwo\n", clientSocketOutput.toString());
        assertEquals("One\nTwo\n", consoleOutput.toString());
    }

}
