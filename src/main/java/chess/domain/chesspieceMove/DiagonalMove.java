package chess.domain.chesspieceMove;

import chess.domain.Position;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiagonalMove implements Move {
    private static DiagonalMove diagonalMove;

    public static DiagonalMove getInstance() {
        if (Objects.isNull(diagonalMove))
            return new DiagonalMove();

        return diagonalMove;
    }

    @Override
    public List<Position> move(Position source, Position target) {
        if (!isInRoute(source, target)) {
            throw new IllegalArgumentException();
        }

        int signX = target.calculateColumnDistance(source) / Math.abs(target.calculateColumnDistance(source));
        int signY = target.calculateRowDistance(source) / Math.abs(target.calculateRowDistance(source));
        int count = Math.abs(source.calculateColumnDistance(target));

        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> Position.of(source.getY() + i * signY, source.getX() + i * signX))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isInRoute(Position source, Position target) {
        return !source.equals(target)
                && Math.abs(source.calculateRowDistance(target)) - Math.abs(source.calculateColumnDistance(target)) == 0;
    }
}
