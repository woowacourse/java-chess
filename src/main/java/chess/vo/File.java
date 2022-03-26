package chess.vo;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;

public enum File {

    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String value;

    File(String value) {
        this.value = value;
    }

    public static File of(String input) {
        return Arrays.stream(values())
            .filter(file -> file.value.equals(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재 하지 않는 파일입니다."));
    }

    public static List<File> traceGroup(File source, File target) {
        return Arrays.stream(values())
            .filter(file -> file.isBetween(source, target))
            .collect(toUnmodifiableList());
    }

    private boolean isBetween(File source, File target) {
        if (source.isBiggerThan(target)) {
            return this.isBiggerThan(target) && source.isBiggerThan(this);
        }
        return this.isBiggerThan(source) && target.isBiggerThan(this);
    }

    private boolean isBiggerThan(File other) {
        return this.compareTo(other) > 0;
    }

    public int displacement(File other) {
        return other.value.compareTo(this.value);
    }
}
