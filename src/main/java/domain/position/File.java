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
    private final int order;

    File(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public static File fromName(String name) {
        return Arrays.stream(values())
                .filter(file -> file.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("rejected value: %s - 존재하지 않은 file입니다.", name)));
    }

    private static File fromOrder(int order) {
        return Arrays.stream(values())
                .filter(file -> file.order == order)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("rejected value: %d - 존재하지 않은 file입니다.", order)));
    }

    public File next() {
        if (this == H) {
            throw new IllegalArgumentException("범위를 벗어난 file입니다.");
        }
        return File.fromOrder(this.order + 1);
    }

    public File prev() {
        if (this == A) {
            throw new IllegalArgumentException("범위를 벗어난 file입니다.");
        }
        return File.fromOrder(this.order - 1);
    }

    public int subtract(File file) {
        return this.order - file.order;
    }
}
