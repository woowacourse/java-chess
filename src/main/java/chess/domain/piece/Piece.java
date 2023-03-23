package chess.domain.piece;

import chess.domain.Position;

public abstract class Piece {
    protected final Color color;
    protected final PieceType type;

    protected Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean canMove(Position from, Position to, Piece piece);

    public abstract boolean canJump();

    public Color getColor() {
        return color;
    }

    public boolean isTypeOf(PieceType type) {
        return this.type == type;
    }

    public PieceType getType() {
        return type;
    }

    public double getScore() {
        return type.getScore();
    }
}
