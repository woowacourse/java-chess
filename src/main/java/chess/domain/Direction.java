package chess.domain;

import chess.domain.board.Position;

import java.util.Arrays;

public enum Direction {
    UP(1, 0),
    DOWN(-1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UP_LEFT(1, -1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(-1, 1),
    KNIGHT(0, 0);

    private final int rank;
    private final int file;

    Direction(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public static Direction findDirection(final Position current, final Position target) {
        if (isKnight(current, target)) {
            return KNIGHT;
        }
        int fileUnit = Integer.compare(target.getFile(), current.getFile());
        int rankUnit = Integer.compare(target.getRank(), current.getRank());

        return Arrays.stream(Direction.values())
                .filter(direction -> direction.rank == rankUnit && direction.file == fileUnit)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 갈 수 없는 방향입니다."));
    }

    private static boolean isKnight(final Position current, final Position target) {
        int rankDifferent = Math.abs(target.getRank() - current.getRank());
        int fileDifferent = Math.abs(target.getFile() - current.getFile());
        return (rankDifferent == 2 && fileDifferent == 1) || (rankDifferent == 1 && fileDifferent == 2);
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}
