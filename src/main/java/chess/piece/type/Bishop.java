package chess.piece.type;

import chess.board.Location;
import chess.piece.location.strategy.BishopLocationStrategy;
import chess.piece.location.strategy.LocationStrategy;

public class Bishop extends Piece {
    private static final char name = 'b';
    private static final LocationStrategy LOCATION_STRATEGY = new BishopLocationStrategy();

    public Bishop(boolean isBlack) {
        super(p(isBlack), LOCATION_STRATEGY);
    }

    private static char p(boolean isBlack) {
        if (isBlack) {
            return Character.toUpperCase(name);
        }
        return name;
    }

    @Override
    public boolean canMove(Location now, Location after) {
        return now.isDiagonal(after);
    }
}

