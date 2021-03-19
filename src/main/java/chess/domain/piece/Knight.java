package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    private Knight(final Location location, final Team team) {
        super(location, team);
    }

    public static Knight of(final Location location, final Team team) {
        return new Knight(location, team);
    }

    @Override
    public boolean isMovable(final Location target) {
        int subX = Math.abs(location.subtractX(target));
        int subY = Math.abs(location.subtractY(target));
        return ((subX == 1 && subY == 2) || (subX == 2 && subY == 1));
    }

    @Override
    public List<Location> findPath(Location target) {
        return Collections.emptyList();
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }
}
