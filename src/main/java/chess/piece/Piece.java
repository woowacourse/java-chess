package chess.piece;

import java.util.Map;
import java.util.Objects;
import chess.Color;
import chess.Position;

public abstract class Piece {
    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract boolean isAbleToMove(Position startPosition, Position endPosition, Map<Position, Piece> board);

    // 주의: instanceof로 하면 안됨
    @Override
    public boolean equals(Object o) {
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
        return Objects.hashCode(color);
    }
}
