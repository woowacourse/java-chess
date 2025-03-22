package chess.piece;

import static chess.position.Movement.LEFT_DOWN;
import static chess.position.Movement.LEFT_UP;
import static chess.position.Movement.RIGHT_DOWN;
import static chess.position.Movement.RIGHT_UP;

import chess.position.Movement;
import java.util.List;

public final class Bishop extends Piece {

    private Bishop(List<Movement> movements, PieceType pieceType) {
        super(movements, pieceType);
    }

    public static Bishop create() {
        return new Bishop(List.of(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN), PieceType.BISHOP);
    }
}
