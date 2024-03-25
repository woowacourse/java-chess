package domain.board.position;

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

    public static File of(final int index) {
        return Arrays.stream(values())
                .filter(file -> file.toIndex() == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("입력된 값: %d, 해당하는 파일이 없습니다", index)));
    }

    public static File from(final String substring) {
        return of(substring.charAt(0) - 'a');
    }

    public int toIndex() {
        return index;
    }
}
