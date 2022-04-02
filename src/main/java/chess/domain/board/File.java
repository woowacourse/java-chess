package chess.domain.board;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public enum File {
    A("A", 1),
    B("B", 2),
    C("C", 3),
    D("D", 4),
    E("E", 5),
    F("F", 6),
    G("G", 7),
    H("H", 8),
    ;

    private static final String INVALID_RANGE = "유효하지 않은 범위입니다.";

    private final String value;
    private final int order;

    File(String value, int order) {
        this.value = value;
        this.order = order;
    }

    public static File from(String value) {
        return Arrays.stream(values())
                .filter(file -> file.value.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(INVALID_RANGE));
    }

    public static List<File> getBetween(File from, File to) {
        File maxOrder = getHigherOrder(from, to);
        File minOrder = getLowerOrder(from, to);

        List<File> files = Arrays.stream(values())
                .filter(file -> file.order < maxOrder.order && file.order > minOrder.order)
                .collect(Collectors.toList());

        if (maxOrder == from) {
            return reverseFiles(files);
        }

        return files;
    }

    private static List<File> reverseFiles(List<File> files) {
        return files.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private static File getHigherOrder(File from, File to) {
        if (from.order > to.order) {
            return from;
        }
        return to;
    }

    private static File getLowerOrder(File from, File to) {
        if (from.order < to.order) {
            return from;
        }
        return to;
    }

    public int calculateDistance(File targetFile) {
        return order - targetFile.order;
    }

    public String getValue() {
        return value.toLowerCase();
    }
}
