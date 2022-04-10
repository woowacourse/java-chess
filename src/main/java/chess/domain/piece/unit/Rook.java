package chess.domain.piece.unit;

import chess.domain.piece.property.Direction;
import chess.domain.piece.property.PieceFeature;
import chess.domain.piece.property.PieceInfo;
import chess.domain.piece.property.Team;
import java.util.List;

public final class Rook extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);
    }

    public Rook(final Team team) {
        super(new PieceInfo(team, PieceFeature.ROOK));
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }
}
