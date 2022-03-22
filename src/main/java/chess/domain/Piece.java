package chess.domain;

import chess.domain.pieces.Type;

import java.util.Objects;

public final class Piece {
    private final Color color;
    private final Type type;

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return color == piece.color && piece.type.getClass() == type.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
