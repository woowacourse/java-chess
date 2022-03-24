package chess.piece;

import chess.exception.UnmovableException;
import chess.position.Position;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public void move(Position to) {
        if (!isMovablePosition(to)) {
            throw new UnmovableException(String.format(
                "%s의 기물을 %s에서 %s로 이동할 수 없습니다.", getClass().getSimpleName(), position, to));
        }
        position = to;
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public Color getColor() {
        return color;
    }

    protected Position getPosition() {
        return position;
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
        return color == piece.color && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "Piece{" +
            "color=" + color +
            ", type=" + getClass().getSimpleName() +
            ", position=" + position +
            '}';
    }

    public abstract boolean isMovablePosition(Position to);
}
