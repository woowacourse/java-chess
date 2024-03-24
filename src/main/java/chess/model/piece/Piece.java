package chess.model.piece;

import chess.model.position.Movement;

import java.util.Objects;

public class Piece {
    protected final Color color;
    private final Type type;

    Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public boolean canMove(Movement movement, Piece target) {
        validateTargetColor(target);
        return type.canMove(movement);
    }

    protected void validateTargetColor(Piece target) {
        if (target.hasColor(color)) {
            throw new IllegalArgumentException("동일한 색상의 기물이 있는 위치로 움직일 수 없습니다.");
        }
    }

    public boolean isEmpty() {
        return Type.NONE == type;
    }

    public boolean hasColor(Color color) {
        return this.color == color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
