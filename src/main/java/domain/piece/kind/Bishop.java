package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.Pieces;
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

    public boolean canMove(final Point movePoint, final List<Piece> pieceList) {
        final Direction direction = this.point.calculate(movePoint);
        if (directionList.contains(direction)) {
            final Pieces pieces = new Pieces(pieceList);
            return notExistPieceInPath(movePoint, pieces) && hasEnemyPieceOrEmpty(movePoint, pieces);
        }
        return false;
    }
}
