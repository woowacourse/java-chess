package chess.domain.chesspiece;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Bishop extends ChessPiece {

    private static final Map<Color, Bishop> cache;
    private static final String NAME = "B";
    private static final Double VALUE = 3.0;

    static {
        cache = Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        Bishop::new));
    }

    private Bishop(final Color color) {
        super(color, NAME);
    }

    public static Bishop from(final Color color) {
        return cache.get(color);
    }

    @Override
    public void checkMovablePosition(final Position from, final Position to,
                                     final Optional<ChessPiece> possiblePiece) {
        final int rankDistance = Math.abs(from.rankDistance(to));
        final int fileDistance = Math.abs(from.fileDistance(to));

        if (fileDistance != rankDistance) {
            throw new IllegalArgumentException(CHECK_POSITION_ERROR_MESSAGE);
        }

        checkTargetPosition(possiblePiece);
    }

    @Override
    public double value() {
        return VALUE;
    }
}
