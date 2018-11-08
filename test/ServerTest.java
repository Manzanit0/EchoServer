import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerTest {
    @Test
    public void opensSocket4200() {
        Server server = new Server();

        try {
            server.start(4200);
        }
        catch (IOException ex) {
            assertTrue("Opening the socket has thrown an exception.", false);
        }

        assertEquals(true, server.isListening());
        assertEquals(4200, server.getPort());
    }
}
