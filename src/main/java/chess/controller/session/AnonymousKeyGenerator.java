package chess.controller.session;

import java.util.concurrent.ThreadLocalRandom;

public class AnonymousKeyGenerator {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    private static final ThreadLocal<Integer> key = new ThreadLocal<>();
    private static final int RANDOM_MIN_VALUE = 10000;

    private AnonymousKeyGenerator() {
    }

    public static int getKey() {
        final Integer integer = key.get();
        if (integer == null) {
            key.set(random.nextInt(RANDOM_MIN_VALUE, Integer.MAX_VALUE));
        }
        return key.get();
    }

    public static void remove() {
        key.remove();
    }
}
