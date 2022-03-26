package chess.domain.chesspiece;

import static chess.domain.position.Direction.N;
import static chess.domain.position.Direction.NE;
import static chess.domain.position.Direction.NW;
import static chess.domain.position.Direction.S;
import static chess.domain.position.Direction.SE;
import static chess.domain.position.Direction.SW;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
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
    private static final int BLACK_MOVABLE_MAX_DISTANCE = 2;
    private static final int WHITE_MOVABLE_MAX_DISTANCE = -2;
    private static final int DEFAULT_PANW_COUNT = 1;

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
        if (pawnCount == DEFAULT_PANW_COUNT) {
            return VALUE;
        }
        return VALUE_BY_SAME_RANK * pawnCount;
    }

    @Override
    public void checkMovablePosition(final Position from, final Position to) {
        if (isMovablePosition(from, to)) {
            return;
        }
        if (isBlackInitPosition(from, to)) {
            return;
        }
        if (isWhiteInitPosition(from, to)) {
            return;
        }
        throw new IllegalArgumentException(CHECK_POSITION_ERROR_MESSAGE);
    }

    private boolean isMovablePosition(final Position from, final Position to) {
        try {
            return pawnMovableDirections(color)
                    .stream()
                    .map(from::toNextPosition)
                    .anyMatch(position -> position.equals(to));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private List<Direction> pawnMovableDirections(final Color color) {
        if (color.isBlack()) {
            return List.of(S, SE, SW);
        }
        return List.of(N, NE, NW);
    }

    private boolean isBlackInitPosition(final Position from, final Position to) {
        if (!color.isBlack()) {
            return false;
        }
        if (!from.isSameFile(BLACK_INIT_FILE)) {
            return false;
        }
        return from.fileDistance(to) <= BLACK_MOVABLE_MAX_DISTANCE;
    }

    private boolean isWhiteInitPosition(final Position from, final Position to) {
        if (color.isBlack()) {
            return false;
        }
        if (!from.isSameFile(WHITE_INIT_FILE)) {
            return false;
        }
        return from.fileDistance(to) >= WHITE_MOVABLE_MAX_DISTANCE;
    }

    @Override
    public double value() {
        return VALUE;
    }
}