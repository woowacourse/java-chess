package chess.domain.piece.constant;

import static chess.domain.piece.constant.Direction.DOWN;
import static chess.domain.piece.constant.Direction.DOWN_DOWN_LEFT;
import static chess.domain.piece.constant.Direction.DOWN_DOWN_RIGHT;
import static chess.domain.piece.constant.Direction.DOWN_LEFT;
import static chess.domain.piece.constant.Direction.DOWN_LEFT_LEFT;
import static chess.domain.piece.constant.Direction.DOWN_RIGHT;
import static chess.domain.piece.constant.Direction.DOWN_RIGHT_RIGHT;
import static chess.domain.piece.constant.Direction.LEFT;
import static chess.domain.piece.constant.Direction.RIGHT;
import static chess.domain.piece.constant.Direction.UP;
import static chess.domain.piece.constant.Direction.UP_LEFT;
import static chess.domain.piece.constant.Direction.UP_LEFT_LEFT;
import static chess.domain.piece.constant.Direction.UP_RIGHT;
import static chess.domain.piece.constant.Direction.UP_RIGHT_RIGHT;
import static chess.domain.piece.constant.Direction.UP_UP_LEFT;
import static chess.domain.piece.constant.Direction.UP_UP_RIGHT;

import java.util.List;

public enum PieceDirections {

    KING(List.of(
            UP, DOWN, RIGHT, LEFT, UP_RIGHT,
            UP_LEFT, DOWN_RIGHT, DOWN_LEFT)),

    QUEEN(List.of(
            UP, DOWN, RIGHT, LEFT, UP_RIGHT,
            UP_LEFT, DOWN_RIGHT, DOWN_LEFT)),

    BISHOP(List.of(
            UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT)),

    ROOK(List.of(
            UP, DOWN, RIGHT, LEFT)),

    KNIGHT(List.of(
            UP_UP_RIGHT, UP_RIGHT_RIGHT, DOWN_DOWN_RIGHT, DOWN_RIGHT_RIGHT,
            UP_UP_LEFT, UP_LEFT_LEFT, DOWN_DOWN_LEFT, DOWN_LEFT_LEFT)),

    WHITE_PAWN(List.of(UP), List.of(UP_RIGHT, UP_LEFT)),
    BLACK_PAWN(List.of(DOWN), List.of(DOWN_RIGHT, DOWN_LEFT)),
    ;

    private final List<Direction> directionsToMove;
    private final List<Direction> directionsToAttack;

    PieceDirections(final List<Direction> directionsToMove) {
        this(directionsToMove, directionsToMove);
    }

    PieceDirections(final List<Direction> directionsToMove, final List<Direction> directionsToAttack) {
        this.directionsToMove = directionsToMove;
        this.directionsToAttack = directionsToAttack;
    }

    public List<Direction> getDirectionsToMove() {
        return directionsToMove;
    }

    public List<Direction> getDirectionsToAttack() {
        return directionsToAttack;
    }
}
