package chess.model;

import java.util.Arrays;

public enum File {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private final int index;
    private final String value;

    File(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static File valueOf(int index) {
        return Arrays.stream(File.values())
                .filter(file -> file.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 값입니다 "));
    }

    public static File of(String value) {
        return Arrays.stream(File.values())
                .filter(file -> file.value.equals(value.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 값입니다 "));
    }

    public int absMinus(File file) {
        return Math.abs(index - file.index);
    }

    public boolean isBiggerThan(File file) {
        return index > file.index;
    }

    public File getNext(int distance) {
        return File.valueOf(index + distance);
    }

    public int minus(File file) {
        return index - file.index;
    }

    public int getGap(File file) {
        int gap = minus(file);
        if (gap == 0) {
            return gap;
        }
        return gap / absMinus(file);
    }
}
