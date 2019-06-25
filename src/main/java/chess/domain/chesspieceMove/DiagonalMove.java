package chess.domain.chesspieceMove;

import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiagonalMove implements Move {
    private static DiagonalMove diagonalMove;

    private DiagonalMove() {
    }

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

        List<Position> positions = new ArrayList<>();

        int signX = (target.calculateColumnDistance(source)) % 2;
        int signY = (target.calculateRowDistance(source)) % 2;
        int count = Math.abs(source.calculateColumnDistance(target));

        for (int i = 1; i <= count; i++) {
            positions.add(Position.of(source.getY() + i * signY, source.getX() + i * signX));
        }
        return positions;
    }

    @Override
    public boolean isInRoute(Position source, Position target) {
        return !source.equals(target)
                && Math.abs(source.calculateRowDistance(target)) - Math.abs(source.calculateColumnDistance(target)) == 0;
    }
}
