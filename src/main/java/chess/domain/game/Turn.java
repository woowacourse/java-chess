package chess.domain.game;

import chess.domain.piece.Color;

public class Turn {

    private final int value;
    private final Color currentTurn;

    public Turn() {
        this(Color.WHITE);
    }

    public Turn(Color currentTurn) {
        this(currentTurn, 1);
    }

    public Turn(Color currentTurn, int value) {
        this.currentTurn = currentTurn;
        this.value = value;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    public Turn changeTurn() {
        if (currentTurn == Color.WHITE) {
            return new Turn(Color.BLACK, value + 1);
        }
        return new Turn(Color.WHITE, value + 1);
    }

    public int getValue() {
        return value;
    }
}
