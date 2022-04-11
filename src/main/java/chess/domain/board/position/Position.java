package chess.domain.board.position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public class Position {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final String RANK_FILE_DELIMITER = "";

    private static final Map<String, Position> CACHE = new HashMap<>(64);

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return CACHE.computeIfAbsent(file.toString() + rank.toString(), ignored -> new Position(file, rank));
    }

    public static Position from(final String positionValue) {
        validatePosition2Value(positionValue);

        final String[] rankAndFile = positionValue.split(RANK_FILE_DELIMITER);
        final String rank = rankAndFile[RANK_INDEX];
        final String file = rankAndFile[FILE_INDEX];

        return of(File.from(file), Rank.from(rank));
    }

    private static void validatePosition2Value(final String positionValue) {
        if (positionValue.length() != 2) {
            throw new IllegalArgumentException("위치 정보가 유효하지 않습니다.");
        }
    }

    public boolean canMove(final Position targetPosition,
                           final BiPredicate<Integer, Integer> movingCondition) {
        final int differenceOfFile = this.file.calculateDifference(targetPosition.file);
        final int differenceOfRank = this.rank.calculateDifference(targetPosition.rank);
        return movingCondition.test(differenceOfFile, differenceOfRank);
    }

    public boolean isOtherPieceInPathToTarget(final Position targetPosition, final List<Position> otherPositions) {
        Position currentPosition = this;
        List<Position> passingPath = new ArrayList<>();

        while (currentPosition != targetPosition) {
            passingPath.add(currentPosition);
            currentPosition = currentPosition.nextPosition(targetPosition);
        }

        return passingPath.stream()
                .anyMatch(path -> path.hasSame(otherPositions));
    }

    private boolean hasSame(final List<Position> others) {
        return others.stream()
                .anyMatch(other -> this == other);
    }

    private Position nextPosition(final Position targetPosition) {
        File nextFile = file.next(targetPosition.file);
        Rank nextRank = rank.next(targetPosition.rank);
        return Position.of(nextFile, nextRank);
    }

    public boolean isInFile(final File file) {
        return this.file == file;
    }

    public boolean isInRank(final Rank rank) {
        return this.rank == rank;
    }

    @Override
    public String toString() {
        return file.toString() + rank.toString();
    }
}
