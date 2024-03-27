package chess.domain.position;

import java.awt.Point;

public record Position(File file, Rank rank) {

    public static Position of(final int file, final int rank) {
        return new Position(File.of(file), Rank.of(rank));
    }

    public static Position of(final Point point) {
        return Position.of(point.x, point.y);
    }

    public Position moveByDistance(final MoveDistance moveDistance) {
        if (isNextPositionInRange(moveDistance)) {
            return new Position(file.move(moveDistance.fileMoveAmount()), rank.move(moveDistance.rankMoveAmount()));
        }
        throw new IllegalStateException("이동이 불가능합니다.");
    }

    public Position left() {
        return new Position(file.left(), rank);
    }

    public Position right() {
        return new Position(file.right(), rank);
    }

    public Position up() {
        return new Position(file, rank.up());
    }

    public Position down() {
        return new Position(file, rank.down());
    }

    public boolean isMinimumFile() {
        return !file.canLeft();
    }

    public boolean isMaximumFile() {
        return !file.canRight();
    }

    public boolean isMinimumRank() {
        return !rank.canDown();
    }

    public boolean isMaximumRank() {
        return !rank.canUp();
    }

    public boolean isNextPositionInRange(final MoveDistance moveDistance) {
        return file.canMove(moveDistance.fileMoveAmount())
                && rank.canMove(moveDistance.rankMoveAmount());
    }
}
