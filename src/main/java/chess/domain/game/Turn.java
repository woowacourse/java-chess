package chess.domain.game;

import chess.domain.piece.Color;

public class Turn {

    private final Color currentTurn;

    public Turn() {
        this(Color.WHITE);
    }

    public Turn(Color currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    public Turn changeTurn() {
        if (currentTurn == Color.WHITE) {
            return new Turn(Color.BLACK);
        }
        return new Turn(Color.WHITE);
    }
}
