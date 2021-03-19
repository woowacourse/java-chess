package chess.domain.board;

import chess.domain.exceptions.InvalidMoveException;

import java.util.Arrays;

public enum YPosition {
    Eight(8),
    Seven(7),
    Six(6),
    Five(5),
    Four(4),
    Three(3),
    Two(2),
    One(1);

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
