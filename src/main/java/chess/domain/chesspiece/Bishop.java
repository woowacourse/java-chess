package chess.domain.chesspiece;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Bishop extends ChessPiece {

    private static final Map<Color, Bishop> cache;
    private static final Double VALUE = 3.0;

    static {
        cache = Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        Bishop::new));
    }

    private Bishop(final Color color) {
        super(color);
    }

    public static Bishop from(final Color color) {
        return cache.get(color);
    }

    @Override
    public void checkMovablePosition(final Position from, final Position to, final ChessPiece chessPiece) {
        final int rankDistance = Math.abs(from.rankDistance(to));
        final int fileDistance = Math.abs(from.fileDistance(to));

        if (fileDistance != rankDistance) {
            throw new IllegalArgumentException(INVALID_TARGET_POSITION);
        }

        checkTargetPosition(chessPiece);
    }

    @Override
    public double value() {
        return VALUE;
    }
}
