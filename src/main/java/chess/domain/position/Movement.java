package chess.domain.position;

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

    public boolean isDiagonalRight() {
        return isDiagonal()
                && (!isRight() && !isUp() || isRight() && isUp());
    }

    public boolean isDiagonalLeft() {
        return isDiagonal()
                && (isRight() && !isUp() || !isRight() && isUp());
    }

    public boolean isUp() {
        return destination.isRankUpper(current);
    }

    public boolean isRight() {
        return destination.isFileRighter(current);
    }

    public int getRankDistance() {
        return current.getRankDistance(destination);
    }

    public int getFileDistance() {
        return current.getFileDistance(destination);
    }

    public boolean isCurrentRank(final Rank rank) {
        return current.isRank(rank);
    }

    public Position getLowerPosition() {
        if (isUp()) {
            return current;
        }

        return destination;
    }

    public Position getLefterPosition() {
        if (isRight()) {
            return current;
        }

        return destination;
    }
}
