package chess;

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

    public static List<File> traceGroup(File sourceFile, File targetFile) {
        return Arrays.stream(values())
            .filter(file -> file.isBetween(sourceFile, targetFile))
            .collect(Collectors.toList());
    }

    private boolean isBetween(File small, File big) {
        return this.isBiggerThan(small) && big.isBiggerThan(this);
    }

    private boolean isBiggerThan(File other) {
        return this.compareTo(other) > 0;
    }

    public int displacement(File other) {
        return other.value.compareTo(this.value);
    }
}
