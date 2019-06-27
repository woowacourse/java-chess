package chess.domain.chessmove;

import chess.domain.Position;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HorizontalMove implements Move {
    private static final int HORIZONTAL_LINE = 1;

    private static HorizontalMove horizontalMove;

    private HorizontalMove() {
    }

    public static HorizontalMove getInstance() {
        if (Objects.isNull(horizontalMove))
            return new HorizontalMove();

        return horizontalMove;
    }

    @Override
    public List<Position> move(Position source, Position target) {
        if (!isInRoute(source, target)) {
            throw new IllegalArgumentException();
        }

        int minX = Math.min(source.getX(), target.getX());
        int maxX = Math.max(source.getX(), target.getX());

        return IntStream.rangeClosed(minX, maxX)
                .filter(i -> Position.of(source.getY(), i) != Position.of(source.getY(), source.getX()))
                .mapToObj(i -> Position.of(source.getY(), i))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isInRoute(Position source, Position target) {
        return !source.equals(target)
                && source.isInLine(target) == HORIZONTAL_LINE;
    }
}