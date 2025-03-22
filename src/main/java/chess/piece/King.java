package chess.piece;

import static chess.position.Movement.DOWN;
import static chess.position.Movement.LEFT;
import static chess.position.Movement.RIGHT;
import static chess.position.Movement.UP;

import chess.position.Movement;
import java.util.List;

public class King extends Piece {

    public King(List<Movement> movements, PieceType pieceType) {
        super(movements, pieceType);
    }

    public static King create() {
        return new King(List.of(UP, DOWN, LEFT, RIGHT), PieceType.KING);
    }
}
