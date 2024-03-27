package chess.domain.piece.slidingpiece;

import static chess.domain.piece.slidingpiece.Direction.DOWN;
import static chess.domain.piece.slidingpiece.Direction.LEFT;
import static chess.domain.piece.slidingpiece.Direction.RIGHT;
import static chess.domain.piece.slidingpiece.Direction.UP;

import chess.domain.piece.Color;
import java.util.Set;

public class Rook extends SlidingPiece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    Set<Direction> directions() {
        return Set.of(UP, DOWN, LEFT, RIGHT);
    }
}
