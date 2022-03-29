package chess.domain.move;

import chess.domain.position.File;
import chess.domain.position.Rank;
import java.util.Arrays;

public enum Direction {

    EAST(1, 0), // 차이를 나타내는 객체, 읽기 좋은 코드를 작성하자
    WEST(-1, 0),
    NORTH(0, 1),
    SOUTH(0, -1),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1), // 일반 방향이랑 특수 방향을 나눠보자
    KNIGHT_EAST_LEFT(2, 1),
    KNIGHT_EAST_RIGHT(2, -1),
    KNIGHT_WEST_LEFT(-2, 1),
    KNIGHT_WEST_RIGHT(-2, -1),
    KNIGHT_NORTH_LEFT(-1, 2),
    KNIGHT_NORTH_RIGHT(1, 2),
    KNIGHT_SOUTH_LEFT(1, -2),
    KNIGHT_SOUTH_RIGHT(-1, -2),
    ;

    private final int fileDistance;
    private final int rankDistance;

    Direction(final int fileDistance, final int rankDistance) {
        this.fileDistance = fileDistance;
        this.rankDistance = rankDistance;
    }

    public static Direction of(final int fileDistance, final int rankDistance) {
        return Arrays.stream(values())
                .filter(value -> isSameDirection(value, fileDistance, rankDistance))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 이동할 수 없는 방향입니다."));
    }

    private static boolean isSameDirection(final Direction direction,
                                           final int fileDistance,
                                           final int rankDistance) {
        return Math.atan2(direction.rankDistance, direction.fileDistance) == Math.atan2(rankDistance, fileDistance);
    }

    public File moveFile(final File file) {
        return file.move(fileDistance);
    }

    public Rank moveRank(final Rank rank) {
        return rank.move(rankDistance);
    }
}
