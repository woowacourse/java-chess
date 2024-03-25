package domain.board;

import domain.piece.info.Direction;
import domain.piece.info.File;
import domain.piece.info.Rank;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Position {
    private static final Pattern VALID_POSITION_INPUT = Pattern.compile("[^a-h][^1-8]");

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position createPosition(final String positionValue) {
        validatePositionCommands(positionValue);
        final File file = convertToFile(positionValue);
        final Rank rank = convertToRank(positionValue);
        return new Position(file, rank);
    }

    private static void validatePositionCommands(final String value) {
        if (Pattern.matches(VALID_POSITION_INPUT.pattern(), value)) {
            throw new IllegalArgumentException("잘못된 위치 값입니다.");
        }
    }

    private static File convertToFile(final String positionValue) {
        final String fileName = String.valueOf(positionValue.charAt(0)).toUpperCase();
        return File.of(fileName);
    }

    private static Rank convertToRank(final String positionValue) {
        try {
            final int rankNumber = Integer.parseInt(String.valueOf(positionValue.charAt(1))) - 1;
            return Rank.of(rankNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 위치 값입니다.");
        }
    }

    public Position next(final Direction direction) {
        try {
            File nextFile = File.of(fileIndex() + direction.file());
            Rank nextRank = Rank.of(rankIndex() + direction.rank());
            return new Position(nextFile, nextRank);
        } catch (IllegalArgumentException e) {
            return this;
        }
    }

    public void isMovable(final List<Position> movablePositions) {
        boolean isThisPositionMovable = findPositionInMovablePositions(movablePositions);
        if (!isThisPositionMovable) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
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
