package chess.controller;

import java.util.function.Consumer;

public class InputProcessor {

    public static void processUntilSuccess(Runnable runnable, Consumer<String> printer) {
        while(true) {
            try {
                runnable.run();
                return;
            } catch(Exception e) {
                printer.accept(e.getMessage());
            }
        }
    }
}
