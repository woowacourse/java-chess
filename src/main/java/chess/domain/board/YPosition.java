package chess.domain.board;

import chess.domain.exceptions.InvalidMoveException;

import java.util.Arrays;

public enum YPosition {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int yPosition;

    YPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public YPosition moveUnit(int unit) {
        return YPosition.matchYPosition(yPosition + unit);
    }

    private static YPosition matchYPosition(int newYPosition) {
        return Arrays.stream(values())
            .filter(element -> element.yPosition == newYPosition)
            .findAny()
            .orElseThrow(InvalidMoveException::new);
    }

    public int difference(YPosition anotherPosition) {
        return anotherPosition.yPosition - this.yPosition;
    }

    public boolean samePosition(int yPosition) {
        return this.yPosition == yPosition;
    }

    public int getValue() {
        return this.yPosition;
    }
}
