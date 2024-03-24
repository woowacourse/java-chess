package chess.domain;

import chess.domain.piece.Position;

public class Movement {

    private final Position current;
    private final Position destination;

    public Movement(final Position current, final Position destination) {
        this.current = current;
        this.destination = destination;
    }

    public boolean isVertical() {
        return current.isSameFile(destination) && !current.isSameRank(destination);
    }

    public boolean isHorizontal() {
        return !current.isSameFile(destination) && current.isSameRank(destination);
    }

    public boolean isDiagonal() {
        return current.getFileDistance(destination) == current.getRankDistance(destination);
    }

    public boolean isDiagonalRightUp() {
        return (isDiagonal() && (isLeft() && isDown() || isRight() && isUp()));
    }

    public boolean isDiagonalLeftUp() {
        return (isDiagonal() && (isRight() && isDown() || isLeft() && isUp()));
    }

    public boolean isUp() {
        return destination.getRank().isUp(current.getRank());
    }

    public boolean isDown() {
        return !isUp();
    }

    public boolean isRight() {
        return destination.getFile().isBigger(current.getFile());
    }

    public boolean isLeft() {
        return !isRight();
    }

    public int getRankDistance() {
        return current.getRankDistance(destination);
    }

    public int getFileDistance() {
        return current.getFileDistance(destination);
    }

    public Position getLefterPosition() {
        if (isRight()) {
            return current;
        }

        return destination;
    }

    public Position getLowerPosition() {
        if (isUp()) {
            return current;
        }

        return destination;
    }

    public Position getCurrent() {
        return current;
    }

    public Position getDestination() {
        return destination;
    }
}
