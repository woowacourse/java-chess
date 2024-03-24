package chess.domain;

import chess.domain.piece.Position;

public class Movement {

    private final Position current;
    private final Position target;

    public Movement(final Position current, final Position target) {
        this.current = current;
        this.target = target;
    }

    public boolean isVertical() {
        return current.isSameFile(target) && !current.isSameRank(target);
    }

    public boolean isHorizontal() {
        return !current.isSameFile(target) && current.isSameRank(target);
    }

    public boolean isDiagonal() {
        return current.getFileDistance(target) == current.getRankDistance(target);
    }

    public boolean isDiagonalRightUp() {
        return (isDiagonal() && (isLeft() && isDown() || isRight() && isUp()));
    }

    public boolean isDiagonalLeftUp() {
        return (isDiagonal() && (isRight() && isDown() || isLeft() && isUp()));
    }

    public boolean isUp() {
        return target.getRank().isUp(current.getRank());
    }

    public boolean isDown() {
        return !isUp();
    }

    public boolean isRight() {
        return target.getFile().isBigger(current.getFile());
    }

    public boolean isLeft() {
        return !isRight();
    }

    public int getRankDistance() {
        return current.getRankDistance(target);
    }

    public int getFileDistance() {
        return current.getFileDistance(target);
    }

    public Position getLefterPosition() {
        if (isRight()) {
            return current;
        }

        return target;
    }

    public Position getLowerPosition() {
        if (isUp()) {
            return current;
        }

        return target;
    }

    public Position getCurrent() {
        return current;
    }

    public Position getTarget() {
        return target;
    }
}
