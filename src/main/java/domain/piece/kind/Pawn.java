package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;

import java.util.List;

import static domain.piece.attribute.point.Direction.*;

public class Pawn extends Piece {
    private static final List<Direction> blackList = List.of(DOWN, DOWN_LEFT, DOWN_RIGHT);
    private static final List<Direction> whiteList = List.of(UP, UP_LEFT, UP_RIGHT);
    private static final Rank WHITE_START_POSITION = Rank.TWO;
    private static final Rank BLACK_START_POSITION = Rank.SEVEN;

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
        if (direction.canNotMovePoint(this.point)) {
            return false;
        }
        return singleCase(movePoint, pieces) || doubleCase(movePoint, pieces);
    }

    private boolean singleCase(final Point movePoint, final Pieces pieces) {
        final Direction direction = this.point.calculate(movePoint);
        return singleCase(movePoint, direction) && notExistPiece(movePoint, pieces);
    }

    private boolean singleCase(final Point movePoint, final Direction direction) {
        if (direction.canMovePoint(this.point)) {
            return direction.movePoint(this.point)
                            .equals(movePoint);
        }
        return false;
    }

    private boolean doubleCase(final Point movePoint, final Pieces pieces) {
        final Direction direction = this.point.calculate(movePoint);
        if (direction.isDiagonal()) {
            return false;
        }
        return doubleCase(movePoint, direction) && notExistPieces(pieces, direction.movePoint(this.point), movePoint);
    }

    private boolean doubleCase(final Point point, final Direction direction) {
        final Point movedPoint = direction.movePoint(this.point);
        if (this.point.getRankIndex() == getStartIndex()) {
            return direction.movePoint(movedPoint)
                            .equals(point);
        }
        return false;
    }

    private boolean canMovePointWithAttack(final Point movePoint, final Pieces pieces) {
        final Direction direction = this.point.calculate(movePoint);
        if (notContainDirection(direction) || direction.isStraight()) {
            return false;
        }
        return singleCase(movePoint, direction) && hasEnemyPiece(movePoint, pieces);
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

    private int getStartIndex() {
        if (this.isBlack()) {
            return BLACK_START_POSITION.ordinal();
        }
        return WHITE_START_POSITION.ordinal();
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.PAWN;
    }
}
