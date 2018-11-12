package core;

import core.client.*;
import core.server.*;

import java.io.IOException;

public class Main {
    public static void main(String... args) throws IOException {
        if ("server".equals(args[0])) {
            ServerFactory.create(4200).start();
        }

        if ("client".equals(args[0])) {
            ClientFactory.create(4200).start();
        }
    }
}
