package chess.domain.piece.unit;

import chess.domain.piece.property.Direction;
import chess.domain.piece.property.PieceFeature;
import chess.domain.piece.property.PieceInfo;
import chess.domain.piece.property.Team;
import java.util.List;

public final class Knight extends SpecificMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = List.of(
                Direction.EAST_NORTHEAST, Direction.EAST_SOUTHEAST, Direction.WEST_NORTHWEST, Direction.WEST_SOUTHWEST, Direction.NORTH_NORTHEAST,
                Direction.NORTH_NORTHWEST, Direction.SOUTH_SOUTHEAST, Direction.SOUTH_SOUTHWEST);
    }

    public Knight(final Team team) {
        super(new PieceInfo(team, PieceFeature.KNIGHT));
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }
}
