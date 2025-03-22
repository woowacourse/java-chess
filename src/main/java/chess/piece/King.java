package chess.piece;

import static chess.position.Movement.DOWN;
import static chess.position.Movement.LEFT;
import static chess.position.Movement.RIGHT;
import static chess.position.Movement.UP;

import chess.position.Movement;
import chess.position.Position;
import java.util.List;

public class King extends Piece {

    public King(List<Movement> movements, PieceType pieceType) {
        super(movements, pieceType);
    }

    public static King create() {
        return new King(List.of(UP, DOWN, LEFT, RIGHT), PieceType.KING);
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
