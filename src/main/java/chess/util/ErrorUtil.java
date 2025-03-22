package chess.util;

import java.util.function.Consumer;

public class ErrorUtil {

    public static void computeError (Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            runnable.run();
        }
    }
}
