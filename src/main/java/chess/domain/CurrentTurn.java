package chess.domain;

import chess.domain.square.piece.Color;

public class CurrentTurn {
    private Color currentTurn;

    public CurrentTurn(Color startTurn) {
        this.currentTurn = startTurn;
    }

    public void change() {
        if (currentTurn == Color.BLACK) {
            currentTurn = Color.WHITE;
            return;
        }
        currentTurn = Color.BLACK;
    }

    public Color value() {
        return currentTurn;
    }
}
