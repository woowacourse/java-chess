package chess.piece;

import static chess.position.Movement.DOWN;
import static chess.position.Movement.LEFT;
import static chess.position.Movement.RIGHT;
import static chess.position.Movement.UP;

import chess.position.Movement;
import java.util.List;

public final class Rook extends Piece {

    public Rook(List<Movement> movements, PieceType pieceType) {
        super(movements, pieceType);
    }

    public static Rook create() {
        return new Rook(List.of(LEFT, RIGHT, UP, DOWN),
                PieceType.ROOk);
    }
}
