package domain.piece.info;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),

    UP_LEFT(-1, 1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1),

    UP_UP_RIGHT(1, 2),
    UP_UP_LEFT(-1, 2),
    UP_RIGHT_RIGHT(2, 1),
    UP_LEFT_LEFT(-2, 1),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_RIGHT_RIGHT(2, -1),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_LEFT_LEFT(-2, -1),

    UP_UP(0, 2),
    DOWN_DOWN(0, -2),
    ;

    private final int file;
    private final int rank;

    Direction(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }


    public static Direction between(final Position current, final Position source) {
        final int fileDiff = current.fileIndex() - source.fileIndex();
        final int rankDiff = current.rankIndex() - source.rankIndex();

        if (isValueExist(Math.abs(fileDiff), Math.abs(rankDiff))) {
            return Direction.of(Math.abs(fileDiff), Math.abs(rankDiff));
        }

        final int diff = Math.abs(fileDiff - rankDiff);

        if (isNotCardinal(fileDiff - diff, rankDiff - diff)) {
            throw new IllegalArgumentException("잘못된 방향입니다");
        }

        return Direction.of(fileDiff - diff, rankDiff - diff);
    }

    public static boolean isNotCardinal(final int file, final int rank) {
        return Stream.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT)
                .anyMatch(r -> r == Direction.of(file, rank));
    }

    public static boolean isValueExist(final int file, final int rank) {
        return Arrays.stream(values())
                .anyMatch(value -> value.file == file && value.rank == rank);
    }

    public static Direction of(final int file, final int rank) {
        return Arrays.stream(values())
                .filter(value -> value.file == file && value.rank == rank)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효한 파일과 랭크를 입력해주세요"));
    }

    public int file() {
        return file;
    }

    public int rank() {
        return rank;
    }
}
