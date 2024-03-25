package chess.model.piece;

import chess.model.position.Movement;

public abstract class Piece {
    private final Color color;

    Piece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Movement movement, Piece target);

    protected void validateTargetColor(Piece target) {
        if (target.hasColor(color)) {
            throw new IllegalArgumentException("동일한 색상의 기물이 있는 위치로 움직일 수 없습니다.");
        }
    }

    protected boolean hasOppositeColorWith(Piece piece) {
        return color.getOpposite() == piece.color;
    }

    public boolean isEmpty() {
        return equals(Empty.getInstance());
    }

    public boolean hasColor(Color color) {
        return this.color == color;
    }
}
