package chess.domain.board.position;

import java.util.Arrays;

public enum Column {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8),
    ;

    private static final String NO_NUMBER_ERROR_MESSAGE = "잘못된 위치 값 입니다.";

    private final String letter;
    private final int number;

    Column(String letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public static Column numberOf(int number) {
        return Arrays.stream(Column.values())
                .filter(file -> file.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_NUMBER_ERROR_MESSAGE));

    }

    public static Column letterOf(String letter) {
        return Arrays.stream(Column.values())
                .filter(file -> file.letter.equals(letter))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_NUMBER_ERROR_MESSAGE));
    }

    public int getNumber() {
        return number;
    }
}
