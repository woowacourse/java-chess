package chess.model.piece;

import chess.model.Color;
import chess.model.position.Distance;
import java.util.List;

public class King extends Piece {

    private static final List<Direction> directions = Direction.king();

    public King(final Color color) {
        super(color, PieceType.KING);
    }

    @Override
    boolean movable(final Distance distance) {
        return directions.stream()
                .anyMatch(direction -> direction.match(distance.rank(), distance.file()));
    }
}
