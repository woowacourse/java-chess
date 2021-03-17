package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;

public abstract class Piece implements Movable {

    protected final Team team;
    protected Location location;

    protected Piece(final Location location, final Team team) {
        this.location = location;
        this.team = team;
    }

    public void move(final Location target) {
        if (isMovable(target)) {
            location = target;
        }
    }
}
