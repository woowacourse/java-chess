package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.Pieces;
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

    public boolean canMove(final Point movePoint, final List<Piece> piecesList) {
        final Pieces pieces = new Pieces(piecesList);
        return canMovePoint(movePoint, pieces) || canMovePointWithAttack(movePoint, pieces);
    }

    private boolean canMovePoint(final Point movePoint, final Pieces pieces) {
        final Direction direction = this.point.calculate(movePoint);
        if (notContainDirection(direction) || direction.isDiagonal()) {
            return false;
        }
        return singleCase(movePoint, direction) && notExistPiece(movePoint, pieces);
    }

    private boolean canMovePointWithAttack(final Point movePoint, final Pieces pieces) {
        final Direction direction = this.point.calculate(movePoint);
        if (notContainDirection(direction) || direction.isStraight()) {
            return false;
        }
        return singleCase(movePoint, direction) && hasEnemyPieceOrEmpty(movePoint, pieces);
    }

    private boolean notContainDirection(final Direction direction) {
        return !containDirection(direction);
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
