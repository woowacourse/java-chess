package domain.board;

import domain.piece.info.Direction;
import domain.piece.info.File;
import domain.piece.info.Rank;
import java.util.List;
import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position createPosition(final String positionValue) {
        final File file = convertToFile(positionValue);
        final Rank rank = convertToRank(positionValue);
        return new Position(file, rank);
    }

    private static File convertToFile(final String positionValue) {
        final String fileName = String.valueOf(positionValue.charAt(0)).toUpperCase();
        return File.of(fileName);
    }

    private static Rank convertToRank(final String positionValue) {
        final int rankNumber = Integer.parseInt(String.valueOf(positionValue.charAt(1))) - 1;
        return Rank.of(rankNumber);
    }

    public Position next(final Direction direction) {
        return new Position(File.of(fileIndex() + direction.file()),
                Rank.of(rankIndex() + direction.rank()));
    }

    public boolean isMovable(final List<Position> movablePositions) {
        boolean isThisPositionMovable = findPositionInMovablePositions(movablePositions);
        if (!isThisPositionMovable) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        return true;
    }

    private boolean findPositionInMovablePositions(final List<Position> movablePositions) {
        return movablePositions.stream()
                .anyMatch(position -> position.equals(this));
    }

    public int fileIndex() {
        return file.index();
    }

    public int rankIndex() {
        return rank.index();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
