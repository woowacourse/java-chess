package domain.piece.unit;

import static domain.piece.property.Direction.allDirections;

import domain.piece.property.PieceInfo;
import domain.piece.property.PieceFeature;
import domain.piece.property.Team;
import domain.piece.property.Direction;
import java.util.List;

public final class King extends SpecificMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = allDirections();
    }

    public King(final Team team) {
        super(new PieceInfo(team, PieceFeature.KING));
    }

    @Override
    protected List<Direction> getDirections() {
        return directions;
    }
}
