package chess.domain;

import chess.domain.square.piece.Color;

public class Turn {
    private Color currentTurn;

    public Turn(Color startTurn) {
        this.currentTurn = startTurn;
    }

    public void change() {
        if (currentTurn == Color.BLACK) {
            currentTurn = Color.WHITE;
            return;
        }
        currentTurn = Color.BLACK;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }
}
