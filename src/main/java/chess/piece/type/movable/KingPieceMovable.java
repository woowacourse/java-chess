package chess.piece.type.movable;

import chess.board.Route;
import chess.location.Location;
import chess.piece.type.Piece;

import java.util.Map;

import static java.lang.Math.abs;

public class KingPieceMovable implements PieceMovable {
    private static final int KING_RANGE = 1;

    @Override
    public boolean canMove(Route route) {
        // TODO : get 제거하고 할 수 있는 방법 존재 ?
        return isKingRange(route.getNow(), route.getDestination());
    }

//    @Override
//    public boolean canMove(Map<Location, Piece> board, Location now, Location after) {
//        // TODO : King, 퀸을 위해 hasNotObstacle 제거해보자
//        return isKingRange(now, after) && hasNotObstacle(board, now, after);
//    }

    private boolean isKingRange(Location now, Location destination) {
        boolean canRowMove =
                abs(now.getRowValue() - destination.getRowValue()) <= KING_RANGE;
        boolean canColMove =
                abs(now.getColValue() - destination.getColValue()) <= KING_RANGE;

        return canRowMove && canColMove;
    }
}
