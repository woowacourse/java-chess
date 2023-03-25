package chess.board;

import chess.piece.Direction;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Direction getDirectionTo(Position targetPosition) {
        final int fileDiff = file.getValueDiff(targetPosition.file);
        final int rankDiff = rank.getValueDiff(targetPosition.rank);
        return Direction.of(fileDiff, rankDiff);
    }

    public double getSlope(Position targetPosition) {
        final int fileValueDiff = file.getValueDiff(targetPosition.file);
        final int rankValueDiff = rank.getValueDiff(targetPosition.rank);
        if (rankValueDiff == 0) {
            throw new IllegalArgumentException("[ERROR] 0으로 나눌 수 없습니다.");
        }
        return Math.abs((double) fileValueDiff / rankValueDiff);
    }

    public int getMoveCount(final Position targetPosition, final Direction direction) {
        if (direction.isHorizontal()) {
            return Math.abs(file.getValueDiff(targetPosition.file));
        }
        if (direction.isVertical()) {
            return Math.abs(rank.getValueDiff(targetPosition.rank));
        }
        if (direction.isDiagonal()) {
            return Math.abs(file.getValueDiff(targetPosition.file));
        }
        return 0;
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
}
