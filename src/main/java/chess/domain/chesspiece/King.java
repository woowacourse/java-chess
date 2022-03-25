package chess.domain.chesspiece;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class King extends ChessPiece {

    private static final Map<Color, King> cache;
    private static final String NAME = "K";
    private static final Double VALUE = 0.0;

    static {
        cache = Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        King::new));
    }

    private King(final Color color) {
        super(color, NAME, VALUE);
    }

    public static King from(final Color color) {
        return cache.get(color);
    }

    @Override
    public void checkMovablePosition(final Position from, final Position to) {
        final int fileDistance = Math.abs(from.fileDistance(to));
        final int rankDistance = Math.abs(from.rankDistance(to));

        if (Math.abs(fileDistance) > 1 || Math.abs(rankDistance) > 1) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }
    }
}
