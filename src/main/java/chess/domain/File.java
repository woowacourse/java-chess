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

    public static final int SKIP_FIRST = 1;
    private final String fileName;
    private final int fileOrder;

    File(String fileName, int fileOrder) {
        this.fileName = fileName;
        this.fileOrder = fileOrder;
    }

    public static File from(String fileName) {
        return Arrays.stream(File.values())
                .filter(it -> it.fileName.equals(fileName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 file 입니다"));
    }

    public static File from(int fileOrder) {
        return Arrays.stream(File.values())
                .filter(it -> it.fileOrder == fileOrder)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 rank 입니다"));
    }

    public int getDifference(File other) {
        return other.fileOrder - fileOrder;
    }

    public List<File> createPath(File other) {
        List<File> files = IntStream.range(Math.min(fileOrder, other.fileOrder), Math.max(fileOrder, other.fileOrder))
                .skip(SKIP_FIRST)
                .mapToObj(File::from)
                .collect(Collectors.toList());
        if (fileOrder > other.fileOrder) {
            Collections.reverse(files);
        }
        return files;
    }
}
