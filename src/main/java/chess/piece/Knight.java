package chess.piece;

import static chess.position.Movement.DOWN_DOWN_LEFT;
import static chess.position.Movement.DOWN_DOWN_RIGHT;
import static chess.position.Movement.LEFT_LEFT_DOWN;
import static chess.position.Movement.LEFT_LEFT_UP;
import static chess.position.Movement.RIGHT_RIGHT_DOWN;
import static chess.position.Movement.RIGHT_RIGHT_UP;
import static chess.position.Movement.UP_UP_LEFT;
import static chess.position.Movement.UP_UP_RIGHT;

import chess.position.Movement;
import java.util.List;

public final class Knight extends Piece {

    public Knight(List<Movement> movements, PieceType pieceType) {
        super(movements, pieceType);
    }

    public static Knight create() {
        return new Knight(
                List.of(LEFT_LEFT_UP, LEFT_LEFT_DOWN, RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN,
                        DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT, UP_UP_LEFT, UP_UP_RIGHT),
                PieceType.KNIGHT);
    }
}
