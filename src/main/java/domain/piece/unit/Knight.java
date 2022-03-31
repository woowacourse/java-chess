package domain.piece.unit;

import static domain.piece.property.Direction.*;

import domain.piece.property.PieceInfo;
import domain.piece.property.PieceFeature;
import domain.piece.property.Team;
import domain.piece.property.Direction;
import java.util.List;

public final class Knight extends SpecificMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = List.of(EAST_NORTHEAST, EAST_SOUTHEAST, WEST_NORTHWEST, WEST_SOUTHWEST, NORTH_NORTHEAST,
                NORTH_NORTHWEST, SOUTH_SOUTHEAST, SOUTH_SOUTHWEST);
    }

    public Knight(final Team team) {
        super(new PieceInfo(team, PieceFeature.KNIGHT));
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
