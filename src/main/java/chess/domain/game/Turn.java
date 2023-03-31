package chess.domain.game;

import chess.domain.piece.Color;

public class Turn {

    private final int value;

    public Turn() {
        this(1);
    }

    public Turn(int value) {
        this.value = value;
    }

    public Color getCurrentTurn() {
        if (value % 2 == 0) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public Turn changeTurn() {
        return new Turn(value + 1);
    }

    public int getValue() {
        return value;
    }
}
