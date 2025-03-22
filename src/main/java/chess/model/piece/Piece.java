package chess.model.piece;

import chess.model.element.Color;

public class Piece {

    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
