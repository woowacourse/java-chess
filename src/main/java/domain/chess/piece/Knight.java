package domain.chess.piece;

import domain.chess.Color;
import domain.chess.Direction;
import domain.chess.Point;

import java.util.List;

import static domain.chess.Direction.*;

public class Knight extends Piece {
    private static final List<Direction> DIRECTION_LIST = List.of(
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
        return DIRECTION_LIST.stream()
                             .filter(direction -> direction.canMovePoint(this.point))
                             .map(direction -> direction.movePoint(this.point))
                             .anyMatch(movePoint::equals);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.KNIGHT;
    }
}
