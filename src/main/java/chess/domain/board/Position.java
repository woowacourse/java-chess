package chess.domain.board;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Position {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final String RANK_FILE_DELIMITER = "";

    private static final List<Position> allPositions;

    static {
        allPositions = Arrays.stream(File.values())
                .map(Position::generatePositionOf)
                .flatMap(List::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<Position> generatePositionOf(final File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> new Position(file, rank))
                .collect(Collectors.toList());
    }

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return allPositions.stream()
                .filter(position -> position.file == file)
                .filter(position -> position.rank == rank)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("위치 정보가 유효하지 않습니다."));
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
}
