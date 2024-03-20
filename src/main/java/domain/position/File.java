package domain.position;

import java.util.Arrays;

public enum File {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String name;
    private final int index;

    private File(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static File fromName(String name) {
        return Arrays.stream(values())
                .filter(file -> file.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("rejected value: %s - 존재하지 않은 file입니다.", name)));
    }
}
