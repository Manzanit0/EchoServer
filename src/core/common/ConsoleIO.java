package core.common;

import java.io.*;

public class ConsoleIO {
    private final PrintStream out;
    private BufferedReader in;

    public ConsoleIO(PrintStream printStream, InputStream inputStream) {
        out = printStream;
        in = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void write(String message) {
        out.println(message);
    }

    public String read() throws IOException {
        return in.readLine();
    }
}
