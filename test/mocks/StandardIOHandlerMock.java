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

    public static StandardIOHandlerMock createMock(ByteArrayOutputStream output) {
        return createMock("", output);
    }

    public static StandardIOHandlerMock createMock(String inputText, ByteArrayOutputStream output) {
        InputStream inputStream = new ByteArrayInputStream(inputText.getBytes());
        PrintStream printStream = new PrintStream(output);

        return new StandardIOHandlerMock(printStream, inputStream);
    }
}
