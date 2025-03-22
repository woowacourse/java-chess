package chess.piece;

import static chess.position.Movement.DOWN;
import static chess.position.Movement.LEFT;
import static chess.position.Movement.LEFT_DOWN;
import static chess.position.Movement.LEFT_UP;
import static chess.position.Movement.RIGHT;
import static chess.position.Movement.RIGHT_DOWN;
import static chess.position.Movement.RIGHT_UP;
import static chess.position.Movement.UP;

import chess.position.Movement;
import java.util.List;

public final class Queen extends Piece {

    public Queen(List<Movement> movements, PieceType pieceType) {
        super(movements, pieceType);
    }

    public static Queen create() {
        return new Queen(List.of(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN,
                LEFT, RIGHT, UP, DOWN)
                , PieceType.QUEEN);
    }
}
