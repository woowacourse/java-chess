package chess.domain.piece;

import static chess.domain.board.Direction.DOWN;
import static chess.domain.board.Direction.DOWN_LEFT;
import static chess.domain.board.Direction.DOWN_RIGHT;
import static chess.domain.board.Direction.LEFT;
import static chess.domain.board.Direction.RIGHT;
import static chess.domain.board.Direction.UP;
import static chess.domain.board.Direction.UP_LEFT;
import static chess.domain.board.Direction.UP_RIGHT;

import chess.domain.board.Direction;
import java.util.Set;

public class Queen extends SlidingPiece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    Set<Direction> directions() {
        return Set.of(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT, UP, DOWN, LEFT, RIGHT);
    }
}
