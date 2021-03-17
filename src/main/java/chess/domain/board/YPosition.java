package chess.domain.board;

import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.strategy.MoveDirection;

import java.util.Arrays;
import java.util.List;

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
            .filter(element -> element.getValue() == newYPosition)
            .findAny()
            .orElseThrow(InvalidMoveException::new);
    }

    public int getValue() {
        return this.yPosition;
    }
}
