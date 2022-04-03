package chess.chessgame;

import chess.piece.Color;

public class Turn {
    private Color color;

    public Turn() {
        this.color = Color.WHITE;
    }

    public Turn(String turn) {
        this.color = Color.valueOf(turn);
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

    public String getColor() {
        return color.getColor();
    }
}
