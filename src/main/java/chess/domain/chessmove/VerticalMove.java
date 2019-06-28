package chess.domain.chessmove;

import chess.domain.Position;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VerticalMove implements Move {
    private static int VERTICAL_LINE = 0;

    private static class VerticalMoveLazyHolder {
        private static final VerticalMove INSTANCE = new VerticalMove();
    }

    public static VerticalMove getInstance() {
        return VerticalMoveLazyHolder.INSTANCE;
    }

    @Override
    public List<Position> move(Position source, Position target) {
        if (!isInRoute(source, target)) {
            throw new IllegalArgumentException();
        }

        int minY = Math.min(source.getY(), target.getY());
        int maxY = Math.max(source.getY(), target.getY());

        return IntStream.rangeClosed(minY, maxY)
                .filter(i -> Position.of(i, source.getX()) != Position.of(source.getY(), source.getX()))
                .mapToObj(i -> Position.of(i, source.getX()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isInRoute(Position source, Position target) {
        return !source.equals(target)
                && source.isInLine(target) == VERTICAL_LINE;
    }
}