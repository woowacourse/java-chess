package chess.chessgame;

import chess.piece.Color;

public class Turn {
    private Color color;

    public Turn() {
        this.color = Color.WHITE;
    }

    public boolean isRightTurn(Color color) {
        return this.color == color;
    }

    public void nextTurn() {
        if (color == Color.BLACK) {
            color = Color.WHITE;
            return;
        }
        if (color == Color.WHITE) {
            color = Color.BLACK;
        }
    }

}
