package chess.piece;

import static chess.position.Movement.LEFT_DOWN;
import static chess.position.Movement.LEFT_UP;
import static chess.position.Movement.RIGHT_DOWN;
import static chess.position.Movement.RIGHT_UP;

import chess.position.Movement;
import chess.position.Position;
import java.util.List;

public final class Bishop extends Piece {

    private Bishop(List<Movement> movements, PieceType pieceType) {
        super(movements, pieceType);
    }

    public static Bishop create() {
        return new Bishop(List.of(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN), PieceType.BISHOP);
    }

    @Override
    public void validateMove(Position start, Position end) {
        for (Movement movement : movements) {
            Position nextPosition = start;
            while (nextPosition.canMove(movement)) { // 보드 끝까지 가면 끝
                nextPosition = nextPosition.move(movement); // 1칸 움직임
                if (nextPosition.equals(end)) { // 가고자 하는 위치랑 일치함
                    return;
                }
            }
        }
        // 일치하는거 못찾음
        throw new IllegalArgumentException("[ERROR] 기물의 이동 규칙에 어긋나는 움직임입니다.");
    }
}
