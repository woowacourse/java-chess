package chess.model.domain.piece;

import static chess.model.domain.piece.Direction.DOWN;
import static chess.model.domain.piece.Direction.DOWN_LEFT_L;
import static chess.model.domain.piece.Direction.DOWN_RIGHT_L;
import static chess.model.domain.piece.Direction.LEFT;
import static chess.model.domain.piece.Direction.LEFT_DOWN;
import static chess.model.domain.piece.Direction.LEFT_DOWN_L;
import static chess.model.domain.piece.Direction.LEFT_UP;
import static chess.model.domain.piece.Direction.LEFT_UP_L;
import static chess.model.domain.piece.Direction.RIGHT;
import static chess.model.domain.piece.Direction.RIGHT_DOWN;
import static chess.model.domain.piece.Direction.RIGHT_DOWN_L;
import static chess.model.domain.piece.Direction.RIGHT_UP;
import static chess.model.domain.piece.Direction.RIGHT_UP_L;
import static chess.model.domain.piece.Direction.UP;
import static chess.model.domain.piece.Direction.UP_LEFT_L;
import static chess.model.domain.piece.Direction.UP_RIGHT_L;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Type {
    ROOK("rook", 5, true,
        Arrays.asList(UP, DOWN, LEFT, RIGHT)),
    KNIGHT("knight", 2.5, true,
        Arrays.asList(UP_LEFT_L, UP_RIGHT_L, DOWN_LEFT_L, DOWN_RIGHT_L, RIGHT_DOWN_L, RIGHT_UP_L,
            LEFT_DOWN_L, LEFT_UP_L)),
    BISHOP("bishop", 3, true,
        Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
    QUEEN("queen", 9, true,
        Arrays.asList(UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
    KING("king", 0, false,
        Arrays.asList(UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
    PAWN("pawn", 1, false, Collections.singletonList(UP));

    private final String letter;
    private final double score;
    private final boolean changeFromPawn;
    private final List<Direction> directions;

    Type(String letter, double score, boolean changeFromPawn, List<Direction> directions) {
        this.letter = letter;
        this.score = score;
        this.changeFromPawn = changeFromPawn;
        this.directions = directions;
    }

    public static Type of(String letter) {
        return Arrays.stream(Type.values())
            .filter(type -> type.letter.equals(letter))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public double getScore() {
        return score;
    }

    public boolean canChangeFromPawn() {
        return changeFromPawn;
    }
}
