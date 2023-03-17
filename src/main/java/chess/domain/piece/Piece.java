package chess.domain.piece;

import chess.domain.Color;
import chess.practiceMove.Direction;

public abstract class Piece {

    private final String name;
    private final Color color;

    public Piece(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String findName() {
        if (color == Color.WHITE) {
            return name.toLowerCase();
        }
        return name.toUpperCase();
    }

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }
}
