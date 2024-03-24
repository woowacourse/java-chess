package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Point;

import java.util.List;

import static domain.piece.attribute.point.Direction.*;

public class Knight extends Piece {
    private static final List<Direction> directionList = List.of(
            UP_UP_LEFT,
            UP_UP_RIGHT,
            RIGHT_UP_RIGHT,
            RIGHT_DOWN_RIGHT,
            DOWN_DOWN_LEFT,
            DOWN_DOWN_RIGHT,
            LEFT_UP_LEFT,
            LEFT_DOWN_LEFT);

    public Knight(final Point point, final Color color) {
        super(point, color);
    }


    public boolean canMove(final Point movePoint, final List<Piece> pieceList) {
        return canMovePoint(movePoint) && hasEnemyPieceOrEmpty(movePoint, new Pieces(pieceList));
    }

    private boolean canMovePoint(final Point movePoint) {
        return directionList.stream()
                            .filter(direction -> direction.canMovePoint(this.point))
                            .map(direction -> direction.movePoint(this.point))
                            .anyMatch(movePoint::equals);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.KNIGHT;
    }
}
