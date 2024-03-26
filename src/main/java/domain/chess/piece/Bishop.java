package domain.chess.piece;

import domain.chess.Color;
import domain.chess.Direction;
import domain.chess.Point;

import java.util.List;

import static domain.chess.Direction.*;

public class Bishop extends Piece {
    private static final List<Direction> DIRECTION_LIST = List.of(DOWN_LEFT, DOWN_RIGHT, UP_LEFT, UP_RIGHT);

    public Bishop(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.BISHOP;
    }

    public boolean canMove(final Point movePoint, final List<Piece> pieceList) {
        final Direction direction = this.point.calculate(movePoint);
        if (DIRECTION_LIST.contains(direction)) {
            final Pieces pieces = new Pieces(pieceList);
            return notExistPieceInPath(movePoint, pieces) && hasEnemyPieceOrEmpty(movePoint, pieces);
        }
        return false;
    }
}
