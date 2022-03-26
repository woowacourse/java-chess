package chess.domain.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum File {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8),
    ;

    private final String file;
    private final int order;

    File(final String file, final int order) {
        this.file = file;
        this.order = order;
    }

    public static File from(final int other) {
        return File.from(getFileName(other));
    }

    public static File from(final String other) {
        return Arrays.stream(File.values())
                .filter(file -> file.file.equals(other))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판 범위를 벗어납니다."));
    }

    public static List<File> sorted() {
        return Arrays.stream(File.values())
                .sorted(Comparator.comparing(File::getOrder))
                .collect(Collectors.toUnmodifiableList());
    }

    public static int difference(final File from, final File to) {
        return Math.abs(from.order - to.order);
    }

    public static int min(final File from, final File to) {
        return Math.min(from.order, to.order);
    }

    public static int max(final File from, final File to) {
        return Math.max(from.order, to.order);
    }

    public int getOrder() {
        return order;
    }

    private static String getFileName(final int order) {
        return Arrays.stream(File.values())
                .filter(file -> file.order == order)
                .findFirst()
                .map(file -> file.file)
                .orElseThrow(() -> new IllegalArgumentException("체스판 범위를 벗어납니다."));
    }
}
