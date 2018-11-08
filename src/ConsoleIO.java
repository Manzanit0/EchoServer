import java.io.PrintStream;

public class ConsoleIO {
    private final PrintStream out;

    public ConsoleIO(PrintStream out) {
        this.out = out;
    }

    public void write(String message) {
        out.println(message);
    }
}
