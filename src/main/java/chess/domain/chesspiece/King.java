package chess.domain.chesspiece;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class King extends ChessPiece {

    private static final Map<Color, King> cache;
    private static final Double VALUE = 0.0;
    private static final int MOVING_DISTANCE = 1;

    static {
        cache = Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        King::new));
    }

    private King(final Color color) {
        super(color);
    }

    public static King from(final Color color) {
        return cache.get(color);
    }

    @Override
    public void checkMovablePosition(final Position from, final Position to, final ChessPiece chessPiece) {
        final int fileDistance = Math.abs(from.fileDistance(to));
        final int rankDistance = Math.abs(from.rankDistance(to));

        if (Math.abs(fileDistance) > MOVING_DISTANCE || Math.abs(rankDistance) > MOVING_DISTANCE) {
            throw new IllegalArgumentException(INVALID_TARGET_POSITION);
        }

        checkTargetPosition(chessPiece);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double value() {
        return VALUE;
    }
}
