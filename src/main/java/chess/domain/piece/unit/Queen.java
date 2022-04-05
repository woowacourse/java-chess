package chess.domain.piece.unit;

import chess.domain.piece.property.Direction;
import chess.domain.piece.property.PieceFeature;
import chess.domain.piece.property.PieceInfo;
import chess.domain.piece.property.Team;
import java.util.List;

public final class Queen extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHEAST, Direction.SOUTHWEST);
    }

    public Queen(final Team team) {
        super(new PieceInfo(team, PieceFeature.QUEEN));
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }
}
