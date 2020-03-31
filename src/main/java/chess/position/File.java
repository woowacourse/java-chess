package chess.position;

import java.util.ArrayList;
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
    NONE("", 0);

    private static final int MINIMUM_DISTANCE = 1;

    private final String name;
    private final int number;

    File(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public static List<File> valuesBetween(File start, File end) {
        if (start.getNumber() > end.getNumber()) {
            return Arrays.stream(values())
                    .sorted(Comparator.reverseOrder())
                    .filter(file -> file.getNumber() < start.getNumber() && file.getNumber() > end.getNumber())
                    .collect(Collectors.toList());
        }
        return Arrays.stream(values())
                .filter(file -> file.getNumber() > start.getNumber() && file.getNumber() < end.getNumber())
                .collect(Collectors.toList());
    }

    public static File[] valuesExceptNone(){
        List<File> valuesExceptNone = new ArrayList(Arrays.asList(values()));
        valuesExceptNone.remove(NONE);
        return valuesExceptNone.toArray(new File[0]);
    }

    private static boolean between(File target, File smaller, File bigger) {
        return target.compareTo(smaller) >= 0 && target.compareTo(bigger) <= 0;
    }

    private static File max(File file1, File file2) {
        if (file1.compareTo(file2) > 0) {
            return file1;
        } else {
            return file2;
        }
    }

    private static File min(File file1, File file2) {
        if (file1.compareTo(file2) < 0) {
            return file1;
        } else {
            return file2;
        }
    }

    public static File of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElse(NONE);
    }

    public static File of(int number) {
        return Arrays.stream(values())
                .filter(value -> value.number == number)
                .findFirst()
                .orElse(NONE);
    }

    public File[] valuesWithDifferenceBelow(int distance) {
        return Arrays.stream(values())
                .filter(file -> findDifference(file) <= distance)
                .toArray(File[]::new);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return this.number;
    }

    public boolean isNear(File other) {
        return Math.abs(this.getNumber() - other.getNumber()) <= MINIMUM_DISTANCE;
    }

    public int findDifference(File other) {
        return Math.abs(this.getNumber() - other.getNumber());
    }

    public File getFileUsingIncreaseAmount(int increaseAmountOfFile) {
        return of(number + increaseAmountOfFile);
    }

    public boolean isNone() {
        return this == NONE;
    }
}
