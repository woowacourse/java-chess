package chess.domain.position;

import chess.domain.piece.Color;
import chess.domain.piece.slidingpiece.Direction;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(int file, int rank) {
        return new Position(File.valueOf(file), Rank.valueOf(rank));
    }

    public int calculateFileDifference(Position otherPosition) {
        return file.subtract(otherPosition.file);
    }

    public int calculateRankDifference(Position otherPosition) {
        return rank.subtract(otherPosition.rank);
    }

    public boolean isOnSameDiagonalAs(Position targetPosition) {
        return (Math.abs(file.subtract(targetPosition.file)) == Math.abs(rank.subtract(targetPosition.rank)))
            && !this.equals(targetPosition);
    }

    public boolean isOnSameFileAs(Position targetPosition) {
        return file.equals(targetPosition.file)
            && !rank.equals(targetPosition.rank);
    }

    public boolean isOnSameRankAs(Position targetPosition) {
        return rank.equals(targetPosition.rank)
            && !file.equals(targetPosition.file);
    }

    public Position nextPosition(Direction direction) {
        return Position.of(file.value() + direction.fileDirection(),
            rank.value() + direction.rankDirection());
    }

    public Position forward(Color color) {
        if (color == Color.WHITE) {
            return Position.of(file.value(), rank.value() + 1);
        }
        if (color == Color.BLACK) {
            return Position.of(file.value(), rank.value() - 1);
        }
        throw new IllegalArgumentException("올바르지 않은 색상입니다.");
    }

    public Position left() {
        return Position.of(file.value() - 1, rank.value());
    }

    public Position right() {
        return Position.of(file.value() + 1, rank.value());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(file, position.file) && Objects.equals(rank, position.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
