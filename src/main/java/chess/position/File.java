package chess.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum File {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    File(int value) {
        this.value = value;
    }

    public static List<File> orderedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparingInt(row -> row.value))
                .collect(Collectors.toList());
    }

    public static List<File> reverseOrderedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.<File>comparingInt(row -> row.value).reversed())
                .collect(Collectors.toList());
    }

    public List<File> getPath(File to) {
        int start = Math.min(value, to.value);
        int end = Math.max(value, to.value);

        if (this.value < to.value) {
            return getPath(orderedValues(), start, end);
        }

        return getPath(reverseOrderedValues(), start, end);
    }

    private List<File> getPath(List<File> orderedFiles, int start, int end) {
        return orderedFiles.stream()
                .filter(file -> start <= file.value && file.value <= end)
                .collect(Collectors.toList());
    }

    public static int distanceBetween(File from, File to) {
        return Math.abs(from.value - to.value);
    }
}
