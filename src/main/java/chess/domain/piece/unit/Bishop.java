package chess.domain.piece.unit;

import chess.domain.piece.property.Direction;
import chess.domain.piece.property.PieceFeature;
import chess.domain.piece.property.PieceInfo;
import chess.domain.piece.property.Team;
import java.util.List;

public final class Bishop extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = List.of(Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHEAST, Direction.SOUTHWEST);
    }

    public Bishop(final Team team) {
        super(new PieceInfo(team, PieceFeature.BISHOP));
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }
}
