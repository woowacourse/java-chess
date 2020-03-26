package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Type {
    ROOK("R", 5, true, Arrays.asList(UP, DOWN, LEFT, RIGHT)),
    KNIGHT("N", 2.5, false, Arrays.asList(UP_LEFT_L, UP_RIGHT_L, DOWN_LEFT_L, DOWN_RIGHT_L
            , RIGHT_DOWN_L, RIGHT_UP_L, LEFT_DOWN_L, LEFT_UP_L)),
    BISHOP("B", 3, true, Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
    QUEEN("Q", 9, false, Arrays.asList(UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
    KING("K", 0, true, Arrays.asList(UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
    PAWN("P", 1, false, Collections.singletonList(UP));

    private final String name;
    private final double score;
    private final boolean repeat;
    private final List<Direction> directions;

    Type(String name, double score, boolean repeat, List<Direction> directions) {
        this.name = name;
        this.score = score;
        this.repeat = repeat;
        this.directions = directions;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
