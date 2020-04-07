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
        return isKingRange(route.getNow(), route.getDestination());
    }

    private boolean isKingRange(Location now, Location destination) {
        boolean canRowMove =
                abs(now.getRowValue() - destination.getRowValue()) <= KING_RANGE;
        boolean canColMove =
                abs(now.getColValue() - destination.getColValue()) <= KING_RANGE;

        return canRowMove && canColMove;
    }
}
