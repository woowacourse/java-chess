package domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {

    private final Side side;

    public Piece(Side side) {
        this.side = side;
    }

    public boolean isRook() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isBishop() {
        return false;
    }

    public boolean isQueen() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isBlack() {
        return side.isBlack();
    }

    public boolean isOpponent(Piece other) {
        return side != other.side;
    }

    public void checkBlockingPiece(Position target, Map<Position, Piece> pieces) {
        if (pieces.containsKey(target) && !pieces.get(target).isOpponent(this)) {
            throw new IllegalArgumentException("target 위치에 같은 팀 기물이 존재합니다.");
        }
        List<Position> positionsExceptTarget = filterPositionsExceptTarget(target, pieces);
        if (!positionsExceptTarget.isEmpty()) {
            throw new IllegalArgumentException("target 위치로 이동하는 경로에 기물이 존재합니다.");
        }
    }

    private List<Position> filterPositionsExceptTarget(Position target, Map<Position, Piece> pieces) {
        return pieces.keySet().stream()
                .filter(key -> key != target)
                .toList();
    }

    public abstract boolean canMove(Position current, Position target, Map<Position, Piece> pieces);

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Piece piece = (Piece) object;
        return side == piece.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side);
    }
}
