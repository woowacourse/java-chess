package chess.model.piece;

import chess.model.position.Movement;

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

    public boolean isType(Type type) {
        return this.type == type;
    }

    public boolean isEmpty() {
        return isType(Type.NONE);
    }

    public boolean hasColor(Color color) {
        return this.color == color;
    }
}
