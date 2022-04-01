package domain.piece.unit;

import static domain.piece.property.Direction.*;

import domain.piece.property.PieceInfo;
import domain.piece.property.PieceFeature;
import domain.piece.property.Team;
import domain.piece.property.Direction;
import java.util.List;

public final class Bishop extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = List.of(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public Bishop(final Team team) {
        super(new PieceInfo(team, PieceFeature.BISHOP));
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }
}
