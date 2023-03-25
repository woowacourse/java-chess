package chess.domain.board;

import java.util.Arrays;

public enum Move {
    EMPTY(0, 0),
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(-1, 1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1),

    UP_UP(0, 2),
    DOWN_DOWN(0, -2),

    UP_UP_LEFT(-1, 2),
    UP_UP_RIGHT(1, 2),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_DOWN_RIGHT(1, -2),
    LEFT_LEFT_UP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1),
    RIGHT_RIGHT_UP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1);

    private final int x;
    private final int y;

    Move(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Move calculateMove(final Square source, final Square target) {
        final int fileMovement = target.getX() - source.getX();
        final int rankMovement = target.getY() - source.getY();

        final Move moveOnValues = findMatchingMoveOnValuesElseEmpty(fileMovement, rankMovement);
        if (moveOnValues != EMPTY) {
            return moveOnValues;
        }

        return findSameDirectionMoveElseEmpty(fileMovement, rankMovement);
    }

    private static Move findMatchingMoveOnValuesElseEmpty(final int fileMovement, final int rankMovement) {
        return Arrays.stream(Move.values())
                .filter(move -> isSameMovement(move, fileMovement, rankMovement))
                .findFirst()
                .orElse(EMPTY);
    }

    private static boolean isSameMovement(final Move move, final int fileMovement, final int rankMovement) {
        return move.x == fileMovement && move.y == rankMovement;
    }

    private static Move findSameDirectionMoveElseEmpty(final int fileMovement, final int rankMovement) {
        return Arrays.stream(Move.values())
                .filter(move -> isSameDirection(move, fileMovement, rankMovement))
                .findFirst()
                .orElse(EMPTY);
    }

    private static boolean isSameDirection(final Move move, final int directionFile, final int directionRank) {
        return move.x == Integer.signum(directionFile) && move.y == Integer.signum(directionRank);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
