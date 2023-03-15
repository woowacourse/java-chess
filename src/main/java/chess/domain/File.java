package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

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

    public File from(int difference) {
        return Arrays.stream(File.values())
                .filter(it -> it.fileOrder == fileOrder + difference)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 rank 입니다"));
    }

    public int getDifference(File other) {
        return other.fileOrder - fileOrder;
    }

    public List<File> createPath(File other) {
        int fileDifference = getDifference(other);
        if (fileDifference == 0) {
            return new ArrayList<>();
        }
        int unit = fileDifference / Math.abs(fileDifference);
        List<File> result = new ArrayList<>();
        int current = unit;
        while (current != fileDifference) {
            File rank = from(current);
            result.add(rank);
            current += unit;
        }
        return result;
    }

    private int getOrderOf(int difference, int value) {
        if (difference >= 0) {
            return value;
        }
        return value * (-1);
    }
}
