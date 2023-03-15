package chess.model.piece;

import chess.model.Color;
import chess.model.position.Distance;
import java.util.List;

public class Knight extends Piece {

    private static final List<Direction> directions = Direction.knight();

    public Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    boolean movable(final Distance distance) {
        return false;
    }
}
