package chess.domain.piece;

import chess.domain.board.Location;

import java.util.List;

public class King extends Piece {
    private static final String KING_NAME = "K";

    public King(Team team) {
        super(KING_NAME, team);
    }

    @Override
    public List<Location> movableLocations(Location target) {
        return null;
    }
}
