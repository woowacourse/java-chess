package chess.domain.piece;

import static chess.domain.direction.Direction.*;

import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public final class Rook extends Piece {

    private static final String ROOK_NAME = "R";
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(UP, DOWN, RIGHT, LEFT);

    public Rook(Color color) {
        super(color, ROOK_NAME, MOVE_DIRECTIONS);
    }

    @Override
    public boolean isSingleMovable() {
        return false;
    }
}
