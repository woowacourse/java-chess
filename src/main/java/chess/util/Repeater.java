package chess.util;

import java.util.function.Supplier;

public final class Repeater {

    private Repeater() {
    }

    public static <T> T repeatUntilNoIAE(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
