package chess.piece.type.movable;

import chess.board.Route;
import chess.location.Location;
import chess.location.NoExistChessLocationException;
import chess.piece.type.Piece;

import java.util.Map;

public class KnightPieceMovable implements PieceMovable {

    @Override
    public boolean canMove(Route route) {
        return isKnightRange(route.getNow(), route.getDestination());
    }

    private boolean isKnightRange(Location now, Location destination) {
        int[] dRow = {2, 2, 1, 1, -1, -1, -2, -2};
        int[] dCol = {1, -1, -2, 2, -2, 2, -1, 1};

        for (int i = 0; i < dRow.length; i++) {
            Location location;

            try {
                location = now.plusBy(dRow[i], dCol[i]);
            } catch (NoExistChessLocationException e) {
                continue;
            }
            if (location.equals(destination)) {
                return true;
            }
        }

        return false;
    }
}
