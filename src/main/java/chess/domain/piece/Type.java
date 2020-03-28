package chess.domain.piece;

import static chess.domain.movement.Direction.DOWN;
import static chess.domain.movement.Direction.DOWN_LEFT_L;
import static chess.domain.movement.Direction.DOWN_RIGHT_L;
import static chess.domain.movement.Direction.LEFT;
import static chess.domain.movement.Direction.LEFT_DOWN;
import static chess.domain.movement.Direction.LEFT_DOWN_L;
import static chess.domain.movement.Direction.LEFT_UP;
import static chess.domain.movement.Direction.LEFT_UP_L;
import static chess.domain.movement.Direction.RIGHT;
import static chess.domain.movement.Direction.RIGHT_DOWN;
import static chess.domain.movement.Direction.RIGHT_DOWN_L;
import static chess.domain.movement.Direction.RIGHT_UP;
import static chess.domain.movement.Direction.RIGHT_UP_L;
import static chess.domain.movement.Direction.UP;
import static chess.domain.movement.Direction.UP_LEFT_L;
import static chess.domain.movement.Direction.UP_RIGHT_L;

import chess.domain.movement.Direction;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Type {
    ROOK("R", 5, Arrays.asList(UP, DOWN, LEFT, RIGHT)),
    KNIGHT("N", 2.5, Arrays.asList(UP_LEFT_L, UP_RIGHT_L, DOWN_LEFT_L, DOWN_RIGHT_L
        , RIGHT_DOWN_L, RIGHT_UP_L, LEFT_DOWN_L, LEFT_UP_L)),
    BISHOP("B", 3, Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
    QUEEN("Q", 9, Arrays.asList(UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
    KING("K", 0, Arrays.asList(UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
    PAWN("P", 1, Collections.singletonList(UP));

    private final String letter;
    private final double score;
    private final List<Direction> directions;

    Type(String letter, double score, List<Direction> directions) {
        this.letter = letter;
        this.score = score;
        this.directions = directions;
    }

    public String getLetter() {
        return letter;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public double getScore() {
        return score;
    }
}
