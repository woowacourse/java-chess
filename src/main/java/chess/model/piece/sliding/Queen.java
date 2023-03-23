package chess.model.piece.sliding;

import chess.model.Color;
import chess.model.piece.PieceType;
import chess.model.position.Direction;
import java.util.Set;

public class Queen extends SlidingPiece {

    private static final Set<Direction> directions = Direction.allDirections();

    public Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    protected boolean isRightDirection(final Direction direction) {
        return directions.stream()
                .anyMatch(it -> it.match(direction.rank(), direction.file()));
    }
}
