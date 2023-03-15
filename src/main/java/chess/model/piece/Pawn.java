package chess.model.piece;

import chess.model.Color;
import chess.model.position.Distance;
import java.util.List;

public class Pawn extends Piece {

    private static final List<Direction> directions = Direction.pawn();

    public Pawn(final Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    boolean movable(final Distance distance) {
        return false;
    }
}
