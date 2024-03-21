package domain;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Point;

import java.util.List;

import static domain.piece.attribute.point.Direction.*;

public class Bishop extends Piece {
    private static final List<Direction> directionList = List.of(DOWN_LEFT, DOWN_RIGHT, UP_LEFT, UP_RIGHT);

    public Bishop(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.BISHOP;
    }

    public boolean canMove(Point point) {
        final Direction direction = this.point.calculate(point);
        return directionList.contains(direction);
    }
}
