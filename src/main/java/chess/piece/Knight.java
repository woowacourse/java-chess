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
import chess.position.Position;
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
