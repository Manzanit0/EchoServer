import core.client.Client;
import core.common.ConsoleIO;
import mocks.SocketMock;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    @Test
    public void clientEchosToConsole() throws IOException {
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        ByteArrayInputStream consoleInput = new ByteArrayInputStream("One\nTwo".getBytes());
        PrintStream printStream = new PrintStream(consoleOutput);
        ConsoleIO consoleIO = new ConsoleIO(printStream, consoleInput);

        ByteArrayOutputStream clientSocketOutput = new ByteArrayOutputStream();
        ByteArrayInputStream clientSocketInput = new ByteArrayInputStream("One\nTwo".getBytes());
        Socket clientSocket = new SocketMock(clientSocketInput, clientSocketOutput);

        Client client = new Client(clientSocket, consoleIO);
        client.start();

        assertEquals("One\nTwo\n", clientSocketOutput.toString());
        assertEquals("One\nTwo\n", consoleOutput.toString());
    }

}
