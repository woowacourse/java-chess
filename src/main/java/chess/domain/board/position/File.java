package chess.domain.board.position;

import java.util.Arrays;

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

    private final String letter;
    private final int number;

    File(String letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public static File numberOf(int number) {
        return Arrays.stream(File.values())
                .filter(file -> file.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 위치 값 입니다."));

    }

    public static File letterOf(String letter) {
        return Arrays.stream(File.values())
                .filter(file -> file.letter.equals(letter))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 위치 값 입니다."));
    }

    public int getNumber() {
        return number;
    }
}
