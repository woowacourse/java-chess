package chess.domain.piece.slidingpiece;

import static chess.domain.piece.slidingpiece.Direction.DOWN;
import static chess.domain.piece.slidingpiece.Direction.DOWN_LEFT;
import static chess.domain.piece.slidingpiece.Direction.DOWN_RIGHT;
import static chess.domain.piece.slidingpiece.Direction.LEFT;
import static chess.domain.piece.slidingpiece.Direction.RIGHT;
import static chess.domain.piece.slidingpiece.Direction.UP;
import static chess.domain.piece.slidingpiece.Direction.UP_LEFT;
import static chess.domain.piece.slidingpiece.Direction.UP_RIGHT;

import chess.domain.piece.Color;
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
