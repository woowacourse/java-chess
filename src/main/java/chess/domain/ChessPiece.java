package chess.domain;

import java.util.Objects;

public class ChessPiece {
    private final PieceColor color;
    private final PieceType type;

    public ChessPiece(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return color == that.color &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
