package chess.domain.piece;

import chess.domain.position.Position;

public abstract class AbstractPiece implements Piece {

    private final Color color;

    protected AbstractPiece(final Color color) {
        this.color = color;
    }

    protected void validateNotSamePosition(Position from, Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("이동하려는 위치가 현재 위치입니다");
        }
    }

    protected void validateNotSameColor(final Piece target) {
        if (isFriend(target)) {
            throw new IllegalArgumentException("같은 색 기물은 잡을 수 없습니다");
        }
    }

    protected void validateTargetPositionIdentity(final Position to, final Piece target) {
        if (!to.equals(target.getPosition())) {
            throw new IllegalArgumentException("가려고 하는 곳과 목표물의 위치가 일치하지 않습니다");
        }
    }

    @Override
    public boolean isFriend(final Piece target) {
        return color == target.getColor();
    }

    @Override
    public boolean isEnemy(final Piece target) {
        return color.isEnemyColor(target.getColor());
    }

    @Override
    public Color getColor() {
        return color;
    }
}
