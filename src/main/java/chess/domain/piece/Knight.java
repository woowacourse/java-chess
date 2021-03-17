package chess.domain.piece;

import chess.domain.board.Location;

import java.util.List;

public class Knight extends Piece {
    private static final String KNIGHT_NAME = "N";

    public Knight(Team team) {
        super(KNIGHT_NAME, team);
    }

    @Override
    public List<Location> movableLocations(Location target) {
        return null;
    }
}
