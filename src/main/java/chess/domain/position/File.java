package chess.domain.position;

import java.util.Arrays;
import java.util.Objects;

public enum File {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8),
    ;

    private final String name;
    private final int value;

    File(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public static File of(final String name) {
        return Arrays.stream(File.values())
                .filter(file -> Objects.equals(file.name, name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 File입니다."));
    }

    public static File of(final int value) {
        return Arrays.stream(File.values())
                .filter(file -> file.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 File입니다."));
    }

    public int calculateDistance(final File file) {
        return value - file.value;
    }

    public File move(final int distance) {
        try {
            return of(value + distance);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("[ERROR] File을 해당 거리만큼 이동시킬 수 없습니다.");
        }
    }
}
