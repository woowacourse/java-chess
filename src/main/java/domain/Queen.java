package domain;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Point;
import factory.DirectionFactory;

import java.util.List;

public class Queen extends Piece {
    private static final List<Direction> directionList = Direction.all();

    public Queen(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.QUEEN;
    }

    public boolean canMove(final Point point) {
        final Direction direction = DirectionFactory.generate(this.getPoint(), point);
        return directionList.contains(direction);
    }
}
