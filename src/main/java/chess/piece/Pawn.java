package chess.piece;

import static chess.position.Movement.DOWN;
import static chess.position.Movement.DOWN_DOWN;
import static chess.position.Movement.LEFT_DOWN;
import static chess.position.Movement.LEFT_UP;
import static chess.position.Movement.RIGHT_DOWN;
import static chess.position.Movement.RIGHT_UP;
import static chess.position.Movement.UP;
import static chess.position.Movement.UP_UP;

import chess.position.Movement;
import java.util.List;

public final class Pawn extends Piece {

    public Pawn(List<Movement> movements, PieceType pieceType) {
        super(movements, pieceType);
    }

    public static Pawn white() {
        return new Pawn(List.of(UP_UP, UP, RIGHT_UP, LEFT_UP), PieceType.PAWN);
    }

    public static Pawn black() {
        return new Pawn(List.of(DOWN_DOWN, DOWN, RIGHT_DOWN, LEFT_DOWN), PieceType.PAWN);
    }
}
