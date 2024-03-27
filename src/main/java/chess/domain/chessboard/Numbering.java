package chess.domain.chessboard;

import java.util.Arrays;

public enum Numbering {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Numbering(int value) {
        this.value = value;
    }

    public static Numbering findNumbering(int value) {
        return Arrays.stream(Numbering.values())
                .filter(numbering -> numbering.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 넘버링 값을 찾을 수 없습니다."));
    }

    public static Numbering findNextNumbering(Numbering numbering) {
        if (numbering == Numbering.EIGHT) {
            throw new IllegalArgumentException("[ERROR] 현재 맨 위쪽입니다. 위쪽 레터링이 존재하지 않습니다.");
        }
        int findNumberingValue = numbering.value + 1;
        return findNumbering(findNumberingValue);
    }

    public static Numbering findPreviousNumbering(Numbering numbering) {
        if (numbering == Numbering.ONE) {
            throw new IllegalArgumentException("[ERROR] 현재 맨 아래쪽입니다. 아래쪽 레터링이 존재하지 않습니다.");
        }
        int findNumberingValue = numbering.value - 1;
        return findNumbering(findNumberingValue);
    }
}
