package chess.domain.piece;

import static chess.domain.direction.Direction.*;

import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public final class King extends Piece {

    private static final String KING_NAME = "K";
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(
            UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);

    public King(Color color) {
        super(color, KING_NAME, MOVE_DIRECTIONS);
    }

    @Override
    public boolean isSingleMove() {
        return true;
    }
}
