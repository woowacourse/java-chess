package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Directions;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public boolean movable(List<Integer> distances, Piece target) {
        final int dx = distances.get(0);
        final int dy = distances.get(1);

        return Directions.isMovableDirection(this, dx, dy) && isOpponent(target);
    }

    private boolean isOpponent(Piece another) {
        return this.color != another.color;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isQueen() {
        return false;
    }

    public boolean isRook() {
        return false;
    }

    public boolean isBishop() {
        return false;
    }

    public boolean isInValid() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isDifferentColor(Color anotherColor) {
        return this.color != anotherColor;
    }

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
        return Objects.hash(color);
    }
}
