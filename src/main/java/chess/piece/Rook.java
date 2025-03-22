package chess.piece;

import static chess.position.Movement.DOWN;
import static chess.position.Movement.LEFT;
import static chess.position.Movement.RIGHT;
import static chess.position.Movement.UP;

import chess.position.Movement;
import chess.position.Position;
import java.util.List;

public final class Rook extends Piece {

    public Rook(List<Movement> movements, PieceType pieceType) {
        super(movements, pieceType);
    }

    public static Rook create() {
        return new Rook(List.of(LEFT, RIGHT, UP, DOWN),
                PieceType.ROOK);
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
