package core.io;

import java.io.*;

public abstract class IOHandler {
    private final PrintStream writer;
    private final BufferedReader reader;

    public IOHandler(PrintStream writer, InputStream inputStream) {
        this.writer = writer;
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void write(String message) {
        writer.println(message);
    }

    public String read() throws IOException {
        return reader.readLine();
    }
}
