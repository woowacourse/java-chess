package chess.piece.location.strategy;

import chess.board.Location;

import java.util.ArrayList;
import java.util.List;

public class PawnLocationStrategy implements LocationStrategy {
    private static final int PAWN_COUNT = 8;
    private static final int PAWN_INTIAL_ROW = 2;
    private static final char PAWN_INTIAL_COL = 'a';
    private static final int REVERSE_DIFFERENCE_COUNT = 5;

    @Override
    public List<Location> getInitialLocation() {
        final List<Location> pawnLocation = new ArrayList<>();
        for (int i = 0; i < PAWN_COUNT; i++) {
            pawnLocation.add(new Location(PAWN_INTIAL_ROW, (char) (PAWN_INTIAL_COL + i)));
        }
        return pawnLocation;
    }

    @Override
    public List<Location> reverseIntialLocation() {
        List<Location> initialLocations = getInitialLocation();
        for (Location location: initialLocations) {
            initialLocations.add(location.moveRowBy(REVERSE_DIFFERENCE_COUNT));
        }
        return initialLocations;
    }
}
