package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;

public class Queen extends Piece {

    private Queen(final Location location, final Team team) {
        super(location, team);
    }

    public static Queen of(Location location, Team team) {
        return new Queen(location, team);
    }

    @Override
    public boolean isMovable(final Location target) {
        return location.isHorizontalOrVertical(target) || location.isDiagonal(target);
    }
}
