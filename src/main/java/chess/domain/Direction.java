package chess.domain;

import chess.domain.board.Position;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {
    UP(Direction::isUp, 1, 0),
    DOWN(Direction::isDown, -1, 0),
    LEFT(Direction::isLeft, 0, -1),
    RIGHT(Direction::isRight, 0, 1),
    UP_LEFT(Direction::isUpLeft, 1, -1),
    UP_RIGHT(Direction::isUpRight, 1, 1),
    DOWN_LEFT(Direction::isDownLeft, -1, -1),
    DOWN_RIGHT(Direction::isDownRight, -1, 1),
    KNIGHT(Direction::isKnight, 0, 0);

    private final BiPredicate<Position, Position> finder;
    private final int rank;
    private final int file;

    Direction(BiPredicate<Position, Position> finder, int rank, int file) {
        this.finder = finder;
        this.rank = rank;
        this.file = file;
    }

    public static Direction findDirection(final Position current, final Position target) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.finder.test(current, target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 갈 수 없는 방향입니다."));
    }

    private static boolean isUp(final Position current, final Position target) {
        int rankDifferent = target.getRank() - current.getRank();
        int fileDifferent = target.getFile() - current.getFile();
        return rankDifferent > 0 && fileDifferent == 0;
    }

    private static boolean isDown(final Position current, final Position target) {
        int rankDifferent = target.getRank() - current.getRank();
        int fileDifferent = target.getFile() - current.getFile();
        return rankDifferent < 0 && fileDifferent == 0;
    }

    private static boolean isLeft(final Position current, final Position target) {
        int rankDifferent = target.getRank() - current.getRank();
        int fileDifferent = target.getFile() - current.getFile();
        return rankDifferent == 0 && fileDifferent < 0;
    }

    private static boolean isRight(final Position current, final Position target) {
        int rankDifferent = target.getRank() - current.getRank();
        int fileDifferent = target.getFile() - current.getFile();
        return rankDifferent == 0 && fileDifferent > 0;
    }

    private static boolean isUpLeft(final Position current, final Position target) {
        int rankDifferent = target.getRank() - current.getRank();
        int fileDifferent = target.getFile() - current.getFile();
        return Math.abs(rankDifferent) == Math.abs(fileDifferent) && rankDifferent > 0 && fileDifferent < 0;
    }

    private static boolean isUpRight(final Position current, final Position target) {
        int rankDifferent = target.getRank() - current.getRank();
        int fileDifferent = target.getFile() - current.getFile();
        return Math.abs(rankDifferent) == Math.abs(fileDifferent) && rankDifferent > 0 && fileDifferent > 0;
    }

    private static boolean isDownLeft(final Position current, final Position target) {
        int rankDifferent = target.getRank() - current.getRank();
        int fileDifferent = target.getFile() - current.getFile();
        return Math.abs(rankDifferent) == Math.abs(fileDifferent) && rankDifferent < 0 && fileDifferent < 0;
    }

    private static boolean isDownRight(final Position current, final Position target) {
        int rankDifferent = target.getRank() - current.getRank();
        int fileDifferent = target.getFile() - current.getFile();
        return Math.abs(rankDifferent) == Math.abs(fileDifferent) && rankDifferent < 0 && fileDifferent > 0;
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
