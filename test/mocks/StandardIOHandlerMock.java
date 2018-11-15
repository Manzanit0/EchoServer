package mocks;

import core.io.IOHandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class StandardIOHandlerMock extends IOHandler {
    private StandardIOHandlerMock(PrintStream output, InputStream input) {
        super(output, input);
    }

    public static IOHandler createMock(String inputText, ByteArrayOutputStream output) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputText.getBytes());
        PrintStream printStream = new PrintStream(output);

        return new StandardIOHandlerMock(printStream, inputStream);
    }
}
