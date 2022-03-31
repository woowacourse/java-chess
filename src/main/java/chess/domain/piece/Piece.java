package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.direction.Direction;

public abstract class Piece {

    public static final double PAWN_LOW_SCORE = 0.5;

    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract Direction matchDirection(Position from, Position to);

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isPawn() {
        return false;
    }
    public boolean isKing() {
        return false;
    }

    public boolean isQueen() {
        return false;
    }

    public boolean isBishop() {
        return false;
    };

    public boolean isRook() {
        return false;
    }

    public boolean isKnight() {
        return false;
    };

    public boolean isWhite() {
        return this.color == Color.WHITE;
    }
}
