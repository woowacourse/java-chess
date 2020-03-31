package chess.piece.type.movable;

import chess.location.Location;
import chess.piece.type.Piece;

import java.util.Map;

import static java.lang.Math.abs;

public class KingPieceMovable implements PieceMovable {
    private static final int KING_RANGE = 1;

    @Override
    public boolean canMove(Map<Location, Piece> board, Location now, Location after) {
        return isKingRange(now, after) && hasNotObstacle(board, now, after);
    }

    private boolean isKingRange(Location now, Location destination) {
        boolean canRowMove =
                abs(now.getRowValue() - destination.getRowValue()) <= KING_RANGE;
        boolean canColMove =
                abs(now.getColValue() - destination.getColValue()) <= KING_RANGE;

        return canRowMove && canColMove;
    }
}
