package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;

public class Rook extends Piece {

    private Rook(final Location location, final Team team) {
        super(location, team);
    }

    public static Rook of(final Location location, final Team team) {
        return new Rook(location, team);
    }

    @Override
    public boolean isMovable(final Location target) {
        return location.isHorizontalOrVertical(target);
    }
}
