package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Point;

import java.util.List;
import java.util.Set;

import static domain.piece.attribute.point.Direction.*;

public class Pawn extends Piece {
    private static final List<Direction> blackList = List.of(DOWN, DOWN_LEFT, DOWN_RIGHT);
    private static final List<Direction> whiteList = List.of(UP, UP_LEFT, UP_RIGHT);
    private static final int DOUBLE_COUNT = 2;
    private static final int SINGLE_COUNT = 1;

    @Override
    protected Set<Point> findLegalMovePoints(Pieces pieces) {
        return null;
    }

    @Override
    protected Piece update(Point point) {
        return null;
    }

    private boolean flag = false;

    public Pawn(final Point point, final Color color) {
        super(point, color);
    }


    public boolean canMove(final Point point) {
        final var direction = this.point.calculate(point);

        if (!containDirection(direction)) {
            return false;
        }
        return singleCase(point, direction) || doubleCase(point, direction);
    }

    private boolean containDirection(final Direction direction) {
        if (this.isBlack()) {
            return blackList.contains(direction);
        }
        return whiteList.contains(direction);
    }

    private boolean singleCase(final Point point, final Direction direction) {
        final var index = this.point.toIndex();
        return Point.fromIndex(index.move(direction, SINGLE_COUNT))
                    .equals(point);
    }

    private boolean doubleCase(final Point point, final Direction direction) {
        if (specialCase(direction)) {
            final var index = this.point.toIndex();
            return Point.fromIndex(index.move(direction, DOUBLE_COUNT))
                        .equals(point);
        }
        return false;
    }

    private boolean specialCase(final Direction direction) {
        return !this.flag && direction.isVerticalStraight();
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.PAWN;
    }
}
