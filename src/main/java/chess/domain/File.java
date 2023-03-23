package chess.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private static final int SKIP_FIRST = 1;
    private final String fileName;
    private final int order;

    File(String fileName, int order) {
        this.fileName = fileName;
        this.order = order;
    }

    public static File from(String fileName) {
        return Arrays.stream(File.values())
                .filter(it -> it.fileName.equals(fileName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 file 입니다"));
    }

    public static File from(int order) {
        return Arrays.stream(File.values())
                .filter(it -> it.order == order)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 file 입니다"));
    }

    public int calculateDifference(File other) {
        return other.order - order;
    }

    public List<File> createPath(File other) {
        List<File> files = IntStream.range(Math.min(order, other.order), Math.max(order, other.order))
                .skip(SKIP_FIRST)
                .mapToObj(File::from)
                .collect(Collectors.toList());
        if (order > other.order) {
            Collections.reverse(files);
        }
        return files;
    }
}
