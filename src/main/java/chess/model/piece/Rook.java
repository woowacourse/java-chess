package chess.model.piece;

import chess.model.Color;
import chess.model.position.Distance;
import java.util.List;

public class Rook extends Piece {

    private static final List<Direction> directions = Direction.rook();

    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    boolean movable(final Distance distance) {
        return directions.stream()
                .anyMatch(direction -> direction.match(distance.rank(), distance.file()));
    }
}
