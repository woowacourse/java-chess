package chess.domain.pieces.piece;

import chess.domain.square.Movement;

import java.util.Objects;

public abstract class Piece {
    private final Color color;
    private final Type type;

    public Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean canMove(final Movement movement, final Piece destinationPiece);

    public boolean isSameColor(final Piece piece) {
        if (piece == null) {
            return false;
        }
        return color == piece.color;
    }

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public Color color() {
        return color;
    }

    public Type type() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
