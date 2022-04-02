package chess.domain.postion;

import java.util.Arrays;

public enum File {
    NOTHING(0),
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int number;

    File(int number) {
        this.number = number;
    }

    public static File from(int candidate) {
        return Arrays.stream(File.values())
                .filter(file -> file.getNumber() == candidate)
                .findAny()
                .orElse(NOTHING);
    }

    public int calculateDifference(final File file) {
        return number - file.getNumber();
    }

    public int calculateAbsoluteValue(File file) {
        return Math.abs(number - file.getNumber());
    }

    public boolean isNothing() {
        return this.equals(NOTHING);
    }

    public int getNumber() {
        return number;
    }
}
