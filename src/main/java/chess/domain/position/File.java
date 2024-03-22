package chess.domain.position;

import java.util.Arrays;

public enum File {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    ;

    private final int number;

    File(int number) {
        this.number = number;
    }

    public static File from(String fileName) {
        return Arrays.stream(values())
                .filter(file -> file.name().equalsIgnoreCase(fileName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 열 번호입니다."));
    }

    public int subtract(File other) {
        return number - other.number;
    }

    public File createFileByDifferenceOf(int fileDifference) {
        return Arrays.stream(values())
                .filter(file -> file.number == number + fileDifference)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 열 번호입니다."));
    }
}
