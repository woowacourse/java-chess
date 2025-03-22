package chess.controller;

import java.util.function.Consumer;
import java.util.function.Supplier;

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

    public static <T> T processUntilSuccess(Supplier<T> supplier, Consumer<String> printer) {
        while(true) {
            try {
                return supplier.get();
            } catch(Exception e) {
                printer.accept(e.getMessage());
            }
        }
    }
}
