package chess.domain.chesspieceMove;

import chess.domain.Position;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VerticalMove implements Move {
    private static VerticalMove verticalMove;

    private VerticalMove() {}

    public static VerticalMove getInstance() {
        if (Objects.isNull(verticalMove))
            return new VerticalMove();

        return verticalMove;
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
                && source.isInLine(target) == 0;
    }
}
