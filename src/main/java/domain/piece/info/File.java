package domain.piece.info;

import java.util.Arrays;

public enum File {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);


    private final int index;

    File(final int index) {
        this.index = index;
    }

    public static File of(final String fileName) {
        return Arrays.stream(values())
                .filter(file -> file.name().equals(fileName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("입력된 값: %s, 해당하는 파일이 없습니다", fileName)));
    }

    public static File of(final int index) {
        return Arrays.stream(values())
                .filter(file -> file.toIndex() == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("입력된 값: %d, 해당하는 파일이 없습니다", index)));
    }

    public int toIndex() {
        return index;
    }
}
