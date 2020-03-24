package chess.piece;

import chess.board.Location;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SetPieceRule {
    private static final List<Location> pawnLocations = makeLocations();

    static {

    }

    private final Map<Piece, List<Location>> mapper;

        private static List<Location> makeLocations(Location... locations) {
            return Arrays.stream(locations)
                    .collect(Collectors.toList());
    }




    public SetPieceRule(final Map<Piece, List<Location>> mapper, final boolean isBlack) {
        this.mapper = mapper;
    }
}
