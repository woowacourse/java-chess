package chess.domain.chesspiece;

import static chess.domain.position.Direction.NE;
import static chess.domain.position.Direction.NW;
import static chess.domain.position.Direction.SE;
import static chess.domain.position.Direction.SW;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Pawn extends ChessPiece {

    private static final Map<Color, Pawn> cache;
    private static final Double VALUE = 1.0;
    private static final Double VALUE_BY_SAME_RANK = 0.5;
    private static final String WHITE_INIT_RANK = "2";
    private static final String BLACK_INIT_RANK = "7";
    private static final int BLACK_MOVABLE_MAX_DISTANCE = 2;
    private static final int BLACK_MOVABLE_DEFAULT_DISTANCE = 1;
    private static final int WHITE_MOVABLE_MAX_DISTANCE = -2;
    private static final int WHITE_MOVABLE_DEFAULT_DISTANCE = -1;
    private static final int DEFAULT_PAWN_COUNT = 1;
    private static final String CROSS_MOVE_TO_EMPTY = "폰은 상대 기물이 존재할 때만 대각선으로 이동할 수 있습니다.";
    private static final String STRAIGHT_MOVE_TO_ENEMY = "폰은 대각선 이동으로만 적을 잡을 수 있습니다.";

    static {
        cache = Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        Pawn::new));
    }

    private Pawn(final Color color) {
        super(color);
    }

    public static Pawn from(final Color color) {
        return cache.get(color);
    }

    public static double calculateScore(final int pawnCount) {
        if (pawnCount == DEFAULT_PAWN_COUNT) {
            return VALUE;
        }
        return VALUE_BY_SAME_RANK * pawnCount;
    }

    @Override
    public void checkMovablePosition(final Position from, final Position to, final ChessPiece targetPiece) {
        if (from.isSameFile(to)) {
            validateStraightMove(from, to, targetPiece);
            return;
        }
        if (isCross(from, to)) {
            validateCrossMove(targetPiece);
            return;
        }
        throw new IllegalArgumentException(INVALID_TARGET_POSITION);
    }

    private void validateStraightMove(final Position from, final Position to, final ChessPiece targetPiece) {
        if (!isMovableDistance(from, to)) {
            throw new IllegalArgumentException(INVALID_TARGET_POSITION);
        }
        if (Objects.isNull(targetPiece)) {
            return;
        }

        checkTargetIsSameColor(targetPiece);
        throw new IllegalArgumentException(STRAIGHT_MOVE_TO_ENEMY);
    }

    private boolean isMovableDistance(final Position from, final Position to) {
        final int distance = from.rankDistance(to);
        if (isDefaultDistance(distance)) {
            return true;
        }
        if (isInitRankPosition(from)) {
            return isMaxDistance(distance);
        }
        return false;
    }

    private boolean isDefaultDistance(final int distance) {
        if (color.isBlack()) {
            return distance == BLACK_MOVABLE_DEFAULT_DISTANCE;
        }
        return distance == WHITE_MOVABLE_DEFAULT_DISTANCE;
    }

    private boolean isInitRankPosition(final Position from) {
        if (color.isBlack()) {
            return from.isSameRank(BLACK_INIT_RANK);
        }
        return from.isSameRank(WHITE_INIT_RANK);
    }

    private boolean isMaxDistance(final int distance) {
        if (color.isBlack()) {
            return distance == BLACK_MOVABLE_MAX_DISTANCE;
        }
        return distance == WHITE_MOVABLE_MAX_DISTANCE;
    }

    private boolean isCross(final Position from, final Position to) {
        try {
            return findCrossDirection()
                    .stream()
                    .map(from::toNextPosition)
                    .anyMatch(position -> position.equals(to));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private List<Direction> findCrossDirection() {
        if (color.isBlack()) {
            return List.of(SE, SW);
        }
        return List.of(NE, NW);
    }

    private void validateCrossMove(final ChessPiece targetPiece) {
        if (Objects.isNull(targetPiece)) {
            throw new IllegalArgumentException(CROSS_MOVE_TO_EMPTY);
        }

        checkTargetIsSameColor(targetPiece);
    }

    private void checkTargetIsSameColor(final ChessPiece targetPiece) {
        if (targetPiece.isSameColor(color)) {
            throw new IllegalArgumentException(TARGET_SAME_COLOR_MESSAGE);
        }
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double value() {
        return VALUE;
    }
}