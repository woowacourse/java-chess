package chess.domain.chesspiece;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Knight extends ChessPiece {

    private static final Map<Color, Knight> cache;
    private static final String NAME = "N";
    private static final Double VALUE = 2.5;

    static {
        cache = Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        Knight::new));
    }

    private Knight(final Color color) {
        super(color, NAME);
    }

    public static Knight from(final Color color) {
        return cache.get(color);
    }

    @Override
    public void checkMovablePosition(final Position from, final Position to) {
        final int fileDistance = Math.abs(from.fileDistance(to));
        final int rankDistance = Math.abs(from.rankDistance(to));

        if (rankDistance + fileDistance != 3) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }

        if (from.isSameFile(to) || from.isSameRank(to)) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }
    }

    @Override
    public Stack<Position> findRoute(final Position from, final Position to) {
        return new Stack<>();
    }

    @Override
    public double value() {
        return VALUE;
    }
}
