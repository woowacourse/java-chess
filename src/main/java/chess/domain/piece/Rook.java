package chess.domain.piece;

import static chess.domain.board.Direction.DOWN;
import static chess.domain.board.Direction.LEFT;
import static chess.domain.board.Direction.RIGHT;
import static chess.domain.board.Direction.UP;

import chess.domain.board.Direction;
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
