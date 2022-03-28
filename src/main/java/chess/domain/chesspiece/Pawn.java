package chess.domain.chesspiece;

import static chess.domain.position.Direction.NE;
import static chess.domain.position.Direction.NW;
import static chess.domain.position.Direction.SE;
import static chess.domain.position.Direction.SW;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Pawn extends ChessPiece {

    private static final Map<Color, Pawn> cache;
    private static final String NAME = "P";
    private static final Double VALUE = 1.0;
    private static final Double VALUE_BY_SAME_RANK = 0.5;
    private static final String WHITE_INIT_FILE = "2";
    private static final String BLACK_INIT_FILE = "7";
    private static final int BLACK_MOVABLE_MAX_DISTANCE = 2;
    private static final int BLACK_MOVABLE_DEFAULT_DISTANCE = 1;
    private static final int WHITE_MOVABLE_MAX_DISTANCE = -2;
    private static final int WHITE_MOVABLE_DEFAULT_DISTANCE = -1;
    private static final int DEFAULT_PAWN_COUNT = 1;

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
        if (pawnCount == DEFAULT_PAWN_COUNT) {
            return VALUE;
        }
        return VALUE_BY_SAME_RANK * pawnCount;
    }

    @Override
    public void checkMovablePosition(final Position from, final Position to,
                                     final Optional<ChessPiece> possiblePiece) {
        if (from.isSameRank(to) && isStraight(from, to)) {
            if (possiblePiece.isPresent()) {
                final ChessPiece targetPiece = possiblePiece.get();
                if (targetPiece.isSameColor(color)) {
                    throw new IllegalArgumentException("같은색 기물입니다.");
                }
                throw new IllegalArgumentException("폰은 대각선 이동으로만 적을 잡을 수 있습니다.");
            }
            return;
        }

        if (isCross(from, to)) {
            if (possiblePiece.isEmpty()) {
                throw new IllegalArgumentException("폰은 상대 기물이 존재할 때만 대각선으로 이동할 수 있습니다.");
            }
            final ChessPiece targetPiece = possiblePiece.get();
            if (targetPiece.isSameColor(color)) {
                throw new IllegalArgumentException("같은색 기물입니다.");
            }
            return;
        }

        throw new IllegalArgumentException(CHECK_POSITION_ERROR_MESSAGE);
    }

    private boolean isCross(final Position from, final Position to) {
        if (color.isBlack()) {
            return Stream.of(SE, SW)
                    .map(from::toNextPosition)
                    .anyMatch(position -> position.equals(to));
        }

        return Stream.of(NE, NW)
                .map(from::toNextPosition)
                .anyMatch(position -> position.equals(to));
    }

    private boolean isStraight(final Position from, final Position to) {
        final int distance = from.fileDistance(to);
        if (color.isBlack()) {
            if (distance == BLACK_MOVABLE_DEFAULT_DISTANCE) {
                return true;
            }
            if (from.isSameFile(BLACK_INIT_FILE)) {
                return distance == BLACK_MOVABLE_MAX_DISTANCE;
            }
            return false;
        }
        if (distance == WHITE_MOVABLE_DEFAULT_DISTANCE) {
            return true;
        }
        if (from.isSameFile(WHITE_INIT_FILE)) {
            return distance == WHITE_MOVABLE_MAX_DISTANCE;
        }
        return false;
    }

    @Override
    public double value() {
        return VALUE;
    }
}