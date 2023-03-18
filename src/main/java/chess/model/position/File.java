package chess.model.position;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum File {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private static final Map<String, File> CACHE = Arrays.stream(values())
            .collect(Collectors.toMap(Enum::toString, file -> file));

    private final int value;

    File(final int value) {
        this.value = value;
    }

    public static File findFile(final String file) {
        final String key = file.toUpperCase();

        validateRank(key);

        return CACHE.get(key);
    }

    private static void validateRank(final String key) {
        if (!CACHE.containsKey(key)) {
            throw new IllegalArgumentException("존재하지 않는 열입니다.");
        }
    }

    public int differ(final File other) {
        return this.value - other.value;
    }

    public int value() {
        return value;
    }
}
