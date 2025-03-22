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
import chess.position.Position;
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

    @Override
    public void validateMove(Position start, Position end) {
        for (Movement movement : movements) {
            if (!start.canMove(movement)) {
                continue;
            }
            Position nextPosition = start.move(movement);
            if (nextPosition.equals(end)) { // 일치하는게 있음
                return;
            }
        }
        // 일치하는거 못찾음
        throw new IllegalArgumentException("[ERROR] 기물의 이동 규칙에 어긋나는 움직임입니다.");
    }


}
