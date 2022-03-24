package chess.domain.position;

import java.util.Arrays;
import java.util.Comparator;
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
    H("h"),
    ;

    private final String file;

    File(final String file) {
        this.file = file;
    }

    public static File from(final String other) {
        return Arrays.stream(File.values())
                .filter(file -> file.file.equals(other))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판 범위를 벗어납니다."));
    }

    public static List<File> sorted() {
        return Arrays.stream(File.values())
                .sorted(Comparator.comparing(File::getFile))
                .collect(Collectors.toUnmodifiableList());
    }

    private String getFile() {
        return file;
    }
}
