package chess.domain.board.position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String text;
    private final int index;

    File(String text, int index) {
        this.text = text;
        this.index = index;
    }

    public static File of(String input) {
        return Arrays.stream(values())
                .filter(file -> file.text.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재 하지 않는 파일입니다."));
    }

    public static File of(int input) {
        return Arrays.stream(values())
                .filter(file -> file.index == input)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재 하지 않는 파일입니다."));
    }

    public static Stream<File> stream() {
        return Arrays.stream(values());
    }

    public static List<File> traceGroup(File source, File target) {
        return Arrays.stream(values())
                .filter(file -> file.isBetween(source, target))
                .collect(Collectors.toList());
    }

    private boolean isBetween(File source, File target) {
        if (source.compareTo(target) > 0) {
            return this.isBiggerThan(target) && source.isBiggerThan(this);
        }
        return this.isBiggerThan(source) && target.isBiggerThan(this);
    }

    private boolean isBiggerThan(File other) {
        return this.compareTo(other) > 0;
    }

    public int displacement(File other) {
        return this.index - other.index;
    }

    public String text() {
        return text;
    }
}
