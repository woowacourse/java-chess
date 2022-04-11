package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;

public abstract class Piece {

    private final Color color;
    private final PieceType type;

    public Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameType(PieceType type) {
        return this.type == type;
    }

    public double getScore() {
        return this.type.getScore();
    }

    public String getColor() {
        return color.name();
    }

    public String getType() {
        return type.name();
    }

    public abstract boolean move(Route route, EmptyPoints emptyPoints);
}
