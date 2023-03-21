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

    private final int file;
    private final int rank;

    Move(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Move calculateMove(final Square source, final Square target) {
        final int fileMovement = target.getFile() - source.getFile();
        final int rankMovement = target.getRank() - source.getRank();

        final Move complexMove = findComplexMoveElseEmpty(fileMovement, rankMovement);
        if (complexMove != EMPTY) {
            return complexMove;
        }

        return findUnitMoveElseEmpty(fileMovement, rankMovement);
    }

    private static Move findComplexMoveElseEmpty(final int fileMovement, final int rankMovement) {
        return Arrays.stream(Move.values())
                .filter(move -> isSameMovement(fileMovement, rankMovement, move))
                .findFirst()
                .orElse(EMPTY);
    }

    private static boolean isSameMovement(final int fileMovement, final int rankMovement, final Move move) {
        return move.file == fileMovement && move.rank == rankMovement;
    }

    private static Move findUnitMoveElseEmpty(final int fileMovement, final int rankMovement) {
        return Arrays.stream(Move.values())
                .filter(move -> isSameDirection(fileMovement, rankMovement, move))
                .findFirst()
                .orElse(EMPTY);
    }

    private static boolean isSameDirection(final int directionFile, final int directionRank, final Move move) {
        return move.file == Integer.signum(directionFile) && move.rank == Integer.signum(directionRank);
    }

    public static boolean isMoveForward(final Move move) {
        return move == Move.UP || move == Move.DOWN;
    }

    public static boolean isMoveDiagonal(final Move move) {
        return move == Move.UP_RIGHT
                || move == Move.DOWN_RIGHT
                || move == Move.UP_LEFT
                || move == Move.DOWN_LEFT;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}
