package chess.domain.piece;

import chess.domain.board.Location;

import java.util.List;

public class Queen extends Piece {
    private static final String QUEEN_NAME = "Q";

    public Queen(Team team) {
        super(QUEEN_NAME, team);
    }

    @Override
    public List<Location> movableLocations(Location target) {
        return null;
    }
}
