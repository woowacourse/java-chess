package chess.domain.piece;

import static chess.domain.board.Direction.DOWN_LEFT;
import static chess.domain.board.Direction.DOWN_RIGHT;
import static chess.domain.board.Direction.UP_LEFT;
import static chess.domain.board.Direction.UP_RIGHT;

import chess.domain.board.Direction;
import java.util.Set;

public class Bishop extends SlidingPiece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    Set<Direction> directions() {
        return Set.of(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    }
}
