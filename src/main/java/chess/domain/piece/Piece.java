package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    private final PieceColor color;
    private final Position position;

    public Piece(PieceColor color, Position position) {
        this.color = color;
        this.position = position;
    }

    public abstract void move(Position target);

    public abstract PieceType getType();

    public boolean isLocated(Position other) {
        return position.equals(other);
    }

    public PieceColor getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }
}
