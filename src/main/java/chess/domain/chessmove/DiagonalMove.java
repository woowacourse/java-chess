package chess.domain.chessmove;

import chess.domain.Position;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiagonalMove implements Move {
    private static final int MIN = 1;

    private static class DiagonalMoveLazyHolder {
        private static final DiagonalMove INSTANCE = new DiagonalMove();
    }

    public static DiagonalMove getInstance() {
        return DiagonalMoveLazyHolder.INSTANCE;
    }

    @Override
    public List<Position> move(Position source, Position target) {
        if (!isInRoute(source, target)) {
            throw new IllegalArgumentException();
        }

        int signX = target.calculateColumnDistance(source) / Math.abs(target.calculateColumnDistance(source));
        int signY = target.calculateRowDistance(source) / Math.abs(target.calculateRowDistance(source));
        int count = Math.abs(source.calculateColumnDistance(target));

        return IntStream.rangeClosed(MIN, count)
                .mapToObj(i -> Position.of(source.getY() + i * signY, source.getX() + i * signX))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isInRoute(Position source, Position target) {
        return !source.equals(target)
                && Math.abs(source.calculateRowDistance(target)) - Math.abs(source.calculateColumnDistance(target)) == 0;
    }
}