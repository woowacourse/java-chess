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

    private static final Map<String, File> NAME_CACHE = Arrays.stream(values())
            .collect(Collectors.toMap(Enum::name, file -> file));
    private static final Map<Integer, File> VALUE_CACHE = Arrays.stream(values())
            .collect(Collectors.toMap(file -> file.value, file -> file));

    private final int value;

    File(final int value) {
        this.value = value;
    }

    public static File findFile(final String fileName) {
        final String key = fileName.toUpperCase();

        validateRank(key);

        return NAME_CACHE.get(key);
    }

    private static void validateRank(final String key) {
        if (!NAME_CACHE.containsKey(key)) {
            throw new IllegalArgumentException("존재하지 않는 열입니다.");
        }
    }

    public int differ(final File other) {
        return this.value - other.value;
    }

    public File findNextFile(final int offer) {
        final Integer nextValue = this.value + offer;

        validateFileValueKey(nextValue);

        return VALUE_CACHE.get(nextValue);
    }

    private void validateFileValueKey(final Integer key) {
        if (!VALUE_CACHE.containsKey(key)) {
            throw new IllegalArgumentException("존재하지 않는 열입니다.");
        }
    }

    public int value() {
        return value;
    }
}
