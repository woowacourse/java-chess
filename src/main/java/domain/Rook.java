package domain;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Point;
import factory.DirectionFactory;

import java.util.List;

public class Rook extends Piece {
    private static final List<Direction> rookList =
            List.of(Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT);

    public Rook(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.ROOK;
    }

    public boolean canMove(final Point point) {
        final Direction direction = DirectionFactory.generate(this.getPoint(), point);
        return rookList.contains(direction);
    }
}
