package chess.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum File {

    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private static final String ERROR_NOT_EXIST_FILE = "[ERROR] 존재 하지 않는 파일입니다.";
    private final String value;

    File(String value) {
        this.value = value;
    }

    static File of(String input) {
        return Arrays.stream(values())
            .filter(file -> file.value.equals(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_EXIST_FILE));
    }

    List<File> betweenFiles(File other) {
        return Arrays.stream(File.values())
            .filter(file -> file.isBetween(this, other))
            .collect(Collectors.toList());
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
