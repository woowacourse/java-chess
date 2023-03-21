package chess.model.piece.type;

import chess.model.Color;
import chess.model.piece.Direction;
import chess.model.piece.PieceType;
import chess.model.position.Distance;
import java.util.List;

public class King extends Piece {

    private static final List<Direction> directions = Direction.allDirections();
    private static final int MAXIMUM_DISTANCE = 1;

    public King(final Color color) {
        super(color, PieceType.KING);
    }

    @Override
    boolean isRightDirection(final Direction direction) {
        return directions.stream()
                .anyMatch(it -> it.match(direction.rank(), direction.file()));
    }

    @Override
    protected boolean isRightDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());
        final int file = Math.abs(distance.file());

        return rank <= MAXIMUM_DISTANCE && MAXIMUM_DISTANCE >= file;
    }
}
