package chess.piece.type;

import chess.board.Location;
import chess.piece.location.strategy.LocationStrategy;
import chess.piece.location.strategy.QueenLocationsStrategy;

public class Queen extends Piece {
    private static final char name = 'q';
    private static final LocationStrategy LOCATION_STRATEGY = new QueenLocationsStrategy();

    public Queen(boolean isBlack) {
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
        return now.isQueenRang(after);
    }
}
