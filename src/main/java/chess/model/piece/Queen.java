package chess.model.piece;

import chess.model.Color;
import chess.model.position.Distance;
import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> directions = Direction.queen();

    public Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    boolean movable(final Distance distance) {
        return false;
    }
}
