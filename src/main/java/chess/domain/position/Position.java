package chess.domain.position;

import java.util.Objects;

import chess.domain.piece.Direction;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Direction getDirectionTo(Position targetPosition) {
        final int fileValuePoint = file.getValuePoint(targetPosition.file);
        final int rankValuePoint = rank.getValuePoint(targetPosition.rank);
        return Direction.from(fileValuePoint, rankValuePoint);
    }

    public double getSlope(Position targetPosition) {
        final int fileValueDiff = file.getValueDiff(targetPosition.file);
        final int rankValueDiff = rank.getValueDiff(targetPosition.rank);
        if (fileValueDiff == 0 || rankValueDiff == 0) {
            return 0;
        }
        return Math.abs((double) fileValueDiff / rankValueDiff);
    }

    public int getMoveCount(final Position targetPosition, final Direction direction) {
        int moveCount = 0;
        if (direction.isHorizontalMovable()) {
            moveCount = file.getValueDiff(targetPosition.file);
        }
        if (direction.isVerticalMovable()) {
            moveCount = rank.getValueDiff(targetPosition.rank);
        }
        return moveCount;
    }

    public Position getNextPosition(final Direction direction) {
        final int nextXPoint = direction.getNextXPoint(file.getValue());
        final int nextYPoint = direction.getNextYPoint(rank.getValue());

        final File nextFile = File.of(nextXPoint);
        final Rank nextRank = Rank.of(nextYPoint);

        return new Position(nextFile, nextRank);
    }

    public int getFile() {
        return file.getValue();
    }

    public int getRank() {
        return rank.getValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
