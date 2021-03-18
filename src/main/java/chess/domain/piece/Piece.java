package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece implements Movable {

    protected final Team team;
    protected Location location;

    protected Piece(final Location location, final Team team) {
        this.location = location;
        this.team = team;
    }

    public final void move(final Location target) {
        if (isMovable(target)) {
            location = target;
        }
    }

    public List<Location> findPath(Location target) {
        List<Location> path = new ArrayList<>();
        int subX = location.subtractX(target);
        int subY = location.subtractY(target);
        int dx = subX == 0 ? 0 : subX / Math.abs(subX);
        int dy = subY == 0 ? 0 : subY / Math.abs(subY);

        Location next = location.moveByStep(dx, dy);
        while (!next.equals(target)) {
            path.add(next);
            next = next.moveByStep(dx, dy);
        }
        return path;
    }
}
