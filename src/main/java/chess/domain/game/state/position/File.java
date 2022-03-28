package chess.domain.game.state.position;

import java.util.Arrays;

public enum File {
    a(1, "a"),
    b(2, "b"),
    c(3, "c"),
    d(4, "d"),
    e(5, "e"),
    f(6, "f"),
    g(7, "g"),
    h(8, "h"),
    Out(-1, "Out");

    private final int value;
    private final String name;

    File(int value, String name) {
        this.value = value;
        this.name = name;
    }

    private static File of(int value) {
        return Arrays.stream(File.values())
            .filter(file -> file.value == value)
            .findFirst()
            .get();
    }

    public static File of(String name) {
        return Arrays.stream(File.values())
            .filter(file -> file.name.equals(name))
            .findFirst()
            .get();
    }

    public File findNext(int next) {
        if (value + next < 1 || value + next > 8) {
            return Out;
        }

        return File.of(value + next);
    }
}
