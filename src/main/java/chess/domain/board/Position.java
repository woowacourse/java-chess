package chess.domain.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;

public class Position {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final String RANK_FILE_DELIMITER = "";

    private static final Map<Integer, Position> allPosition = initialAllPosition();

    private static Map<Integer ,Position> initialAllPosition() {
        Map<Integer, Position> allPositions = new HashMap<>();
        for (File file : File.values()) {
            putAllRankOfFile(allPositions, file);
        }
        return allPositions;
    }

    private static void putAllRankOfFile(Map<Integer, Position> allPositions, File file) {
        for(Rank rank : Rank.values()) {
            allPositions.put(getKey(file, rank), new Position(file, rank));
        }
    }

    private final File file;

    private final Rank rank;
    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        int key = getKey(file, rank);
        if (allPosition.containsKey(key)) {
            return allPosition.get(key);
        }
        return new Position(file, rank);
    }

    private static int getKey(File file, Rank rank) {
        return Objects.hash(file, rank);
    }

    public static Position from(final String positionValue) {
        validatePositionValue(positionValue);

        final String[] rankAndFile = positionValue.split(RANK_FILE_DELIMITER);
        final String rank = rankAndFile[RANK_INDEX];
        final String file = rankAndFile[FILE_INDEX];

        return of(File.from(file), Rank.from(rank));
    }

    private static void validatePositionValue(final String positionValue) {
        if (positionValue.length() != 2) {
            throw new IllegalArgumentException("위치 정보가 유효하지 않습니다.");
        }
    }

    public void validateTargetPosition(final Position targetPosition,
                                       final BiPredicate<Integer, Integer> movingCondition,
                                       final boolean absoluteFlag) {
        final int differenceOfFile = this.file.calculateDifference(targetPosition.file, absoluteFlag);
        final int differenceOfRank = this.rank.calculateDifference(targetPosition.rank, absoluteFlag);

        if (!movingCondition.test(differenceOfFile, differenceOfRank)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    public void checkOtherPiecesInPathToTarget(final Position targetPosition, final List<Position> positions) {
        if (this == targetPosition) {
            return;
        }

        if (positions.stream()
                .anyMatch(another -> this == another)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 존재합니다.");
        }

        nextPosition(targetPosition)
                .checkOtherPiecesInPathToTarget(targetPosition, positions);
    }

    private Position nextPosition(final Position targetPosition) {
        File nextFile = file.next(targetPosition.file);
        Rank nextRank = rank.next(targetPosition.rank);
        return Position.of(nextFile, nextRank);
    }

    public boolean isInFile(final File file) {
        return this.file == file;
    }

    public String toFileRankString() {
        return file.getValue() + rank.getValue();
    }
}
