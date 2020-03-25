package chess.domain.chesspiece;

import java.util.Arrays;
import java.util.List;

import static chess.domain.chesspiece.Direction.*;

public enum MoveRules {
    KING(Arrays.asList(Direction.values())),
    QUEEN(Arrays.asList(Direction.values())),
    BISHOP(Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
    KNIGHT(Arrays.asList()),
    PAWN(Arrays.asList(UP, RIGHT_UP, LEFT_UP)),
    ROOK(Arrays.asList(UP, DOWN, LEFT, RIGHT)),
    BLANK(Arrays.asList());

    private final List<Direction> directions;

    MoveRules(List<Direction> directions) {
        this.directions = directions;
    }

    public List<Direction> getDirections() {
        return directions;
    }
}
