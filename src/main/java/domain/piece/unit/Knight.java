package domain.piece.unit;

import static domain.piece.property.Direction.knightDirections;

import domain.piece.property.PieceInfo;
import domain.piece.property.PieceFeature;
import domain.piece.property.Team;
import domain.piece.property.Direction;
import domain.position.Position;
import java.util.List;

public final class Knight extends SpecificMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = knightDirections();
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
