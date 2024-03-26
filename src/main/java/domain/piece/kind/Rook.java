package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Point;

import java.util.List;
import java.util.Set;

import static domain.piece.attribute.point.Direction.*;

public class Rook extends Piece {
    private static final List<Direction> rookList = List.of(UP, DOWN, RIGHT, LEFT);

    public Rook(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.ROOK;
    }

    @Override
    protected Set<Point> findLegalMovePoints(Pieces pieces) {
        return null;
    }

    @Override
    protected Piece update(Point point) {
        return null;
    }

    public boolean canMove(final Point point) {
        final var direction = this.point.calculate(point);
        return rookList.contains(direction);
    }
}
