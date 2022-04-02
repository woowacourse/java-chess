package chess.domain.board.position;

import java.util.Arrays;

public enum Column {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private static final String NO_NUMBER_ERROR_MESSAGE = "잘못된 위치 값 입니다.";

    private final int number;

    Column(int number) {
        this.number = number;
    }

    public static Column numberOf(int number) {
        return Arrays.stream(Column.values())
                .filter(file -> file.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_NUMBER_ERROR_MESSAGE));
    }

    public int getNumber() {
        return number;
    }
}
