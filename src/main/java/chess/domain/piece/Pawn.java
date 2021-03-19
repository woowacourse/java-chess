package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final int BLACK_PAWN_INITIAL_Y = 7;
    private static final int WHITE_PAWN_INITIAL_Y = 2;

    private Pawn(final Location location, final Team team) {
        super(location, team);
    }

    public static Pawn of(Location location, Team team) {
        return new Pawn(location, team);
    }

    @Override
    public boolean isMovable(final Location target) {
        List<Location> nextLocations = getNextLocations(target);

        return nextLocations.stream()
            .anyMatch(location -> location.equals(target));
    }

    private List<Location> getNextLocations(Location target) {
        List<Location> locations = new ArrayList<>();
        int dy = 1;
        if (team.isBlack()) {
            dy = -1;
        }
        for (int dx = -1; dx <= 1; dx++) {
            if (location.isRangeByStep(dx, 1)) {
                locations.add(location.moveByStep(dx, dy));
            }
        }
        if (isInitialLocation() && location.isRangeByStep(0, 2 * dy)) {
            locations.add(location.moveByStep(0, 2 * dy));
        }
        return locations;
    }

    private boolean isInitialLocation() {
        return (team.isBlack() && location.isSameY(BLACK_PAWN_INITIAL_Y))
            || (team.isWhite() && location.isSameY(WHITE_PAWN_INITIAL_Y));
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }
}
