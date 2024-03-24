package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Point;

import java.util.List;

import static domain.piece.attribute.point.Direction.*;

public class Pawn extends Piece {
    private static final List<Direction> blackList = List.of(DOWN, DOWN_LEFT, DOWN_RIGHT);
    private static final List<Direction> whiteList = List.of(UP, UP_LEFT, UP_RIGHT);

    public Pawn(final Point point, final Color color) {
        super(point, color);
    }


    public boolean canMove(final Point point) {
        final Direction direction = this.point.calculate(point);

        if (!containDirection(direction)) {
            return false;
        }
        return singleCase(point, direction);
    }

    private boolean containDirection(final Direction direction) {
        if (this.isBlack()) {
            return blackList.contains(direction);
        }
        return whiteList.contains(direction);
    }

    private boolean singleCase(final Point point, final Direction direction) {
        return direction.movePoint(this.point)
                        .equals(point);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.PAWN;
    }
}
