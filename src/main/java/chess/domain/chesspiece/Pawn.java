package chess.domain.chesspiece;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Pawn extends ChessPiece {

    private static final Map<Color, Pawn> cache;
    private static final String NAME = "P";
    private static final Double VALUE = 1.0;
    private static final Double VALUE_BY_SAME_RANK = 0.5;
    private static final String WHITE_INIT_FILE = "2";
    private static final String BLACK_INIT_FILE = "7";

    static {
        cache = Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        Pawn::new));
    }

    private Pawn(final Color color) {
        super(color, NAME);
    }

    public static Pawn from(final Color color) {
        return cache.get(color);
    }

    public static double calculateScore(final int pawnCount) {
        if (pawnCount == 1) {
            return VALUE;
        }
        return VALUE_BY_SAME_RANK * pawnCount;
    }

    @Override
    public void checkMovablePosition(final Position from, final Position to) {
        if (from.isSameRank(to)) {
            checkStraightMove(from, to);
            return;
        }
        checkCrossMove(from, to);
    }

    private void checkStraightMove(final Position from, final Position to) {
        if (color.isBlack() && isValidDistance(from, to, 1, BLACK_INIT_FILE)) {
            return;
        }
        if (!color.isBlack() && isValidDistance(from, to, -1, WHITE_INIT_FILE)) {
            return;
        }
        throw new IllegalArgumentException(CHECK_POSITION_ERROR_MESSAGE);
    }

    private boolean isValidDistance(final Position from, final Position to, final int movableDistance,
                                    final String initFile) {
        final int fileDistance = from.fileDistance(to);
        if (movableDistance == fileDistance) {
            return true;
        }
        if (from.isSameFile(initFile)) {
            return fileDistance == movableDistance * 2;
        }
        return false;
    }

    private void checkCrossMove(final Position from, final Position to) {
        final int fileDistance = from.fileDistance(to);
        if (Math.abs(from.rankDistance(to)) != 1) {
            throw new IllegalArgumentException(CHECK_POSITION_ERROR_MESSAGE);
        }
        if (color.isBlack() && fileDistance == 1) {
            return;
        }
        if (!color.isBlack() && fileDistance == -1) {
            return;
        }

        throw new IllegalArgumentException(CHECK_POSITION_ERROR_MESSAGE);
    }

    @Override
    public double value() {
        return VALUE;
    }
}