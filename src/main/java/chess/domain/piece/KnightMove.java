package chess.domain.piece;

import chess.domain.Square;
import java.util.Arrays;

public enum KnightMove {
    UP_RIGHT(1, 2),
    RIGHT_UP(2, 1),
    RIGHT_DOWN(2, -1),
    DOWN_RIGHT(1, -2),
    DOWN_LEFT(-1, -1),
    LEFT_DOWN(-2, -1),
    LEFT_UP(-2, 1),
    UP_LEFT(-1, 2);

    private final int file;
    private final int rank;

    KnightMove(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static KnightMove calculateDirection(final Square source, final Square target) {
        final int directionFile = target.getFile() - source.getFile();
        final int directionRank = target.getRank() - source.getRank();

        return Arrays.stream(KnightMove.values())
                .filter(knightMove -> knightMove.file == directionFile && knightMove.rank == directionRank)
                .findFirst()
                .orElseThrow();
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}
