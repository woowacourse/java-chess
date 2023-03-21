package chess.model.piece.nonsliding;

import static chess.model.position.Direction.NORTH_EAST_EAST;
import static chess.model.position.Direction.NORTH_NORTH_EAST;
import static chess.model.position.Direction.NORTH_NORTH_WEST;
import static chess.model.position.Direction.NORTH_WEST_WEST;
import static chess.model.position.Direction.SOUTH_EAST_EAST;
import static chess.model.position.Direction.SOUTH_SOUTH_EAST;
import static chess.model.position.Direction.SOUTH_SOUTH_WEST;
import static chess.model.position.Direction.SOUTH_WEST_WEST;

import chess.model.Color;
import chess.model.piece.Piece;
import chess.model.piece.PieceType;
import chess.model.position.Direction;
import java.util.Set;

public class Knight extends Piece {

    private static final Set<Direction> directions = Set.of(
            NORTH_NORTH_EAST, NORTH_NORTH_WEST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST,
            NORTH_WEST_WEST, NORTH_EAST_EAST, SOUTH_WEST_WEST, SOUTH_EAST_EAST
    );

    public Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    protected boolean isRightDirection(final Direction direction) {
        return directions.stream()
                .anyMatch(it -> it.match(direction.rank(), direction.file()));
    }
}
