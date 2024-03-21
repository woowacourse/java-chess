package domain;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Index;
import domain.piece.attribute.point.Point;

import java.util.List;

public class Pawn extends Piece {
    private static List<Direction> blackList = List.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
    private static List<Direction> whiteList = List.of(Direction.UP, Direction.UP_LEFT, Direction.UP_RIGHT);
    private static final int DOUBLE_COUNT = 2;
    private static final int SINGLE_COUNT = 1;

    private boolean flag = false;

    public Pawn(final Point point, final Color color) {
        super(point, color);
    }


    public boolean canMove(Point point) {
        Direction direction = this.point.calculate(point);

        if (!containDirection(direction)) {
            return false;
        }
        return singleCase(point, direction) || doubleCase(point, direction);
    }

    private boolean containDirection(Direction direction) {
        if (this.isEqualColor(Color.BLACK)) {
            return blackList.contains(direction);
        }
        return whiteList.contains(direction);
    }

    private boolean singleCase(Point point, Direction direction) {
        Index index = this.point.toIndex();
        return Point.fromIndex(index.move(direction, SINGLE_COUNT))
                    .equals(point);
    }

    private boolean doubleCase(Point point, Direction direction) {
        if (specialCase(direction)) {
            Index index = this.point.toIndex();
            return Point.fromIndex(index.move(direction, DOUBLE_COUNT))
                        .equals(point);
        }
        return false;
    }

    private boolean specialCase(Direction direction) {
        return !this.flag && direction.isStraight();
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.PAWN;
    }
}
