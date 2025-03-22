package chess.piece;

import chess.position.Movement;
import chess.position.Position;
import java.util.List;

public abstract class Piece {

    protected final List<Movement> movements;
    protected final PieceType pieceType;
    private boolean moved = false;


    public Piece(List<Movement> movements, PieceType pieceType) {
        this.movements = movements;
        this.pieceType = pieceType;
    }

    public abstract void validateMove(Position start, Position end);

    public Movement getValidateMovement(Position start, Position end) {
        for (Movement movement : movements) {
            Position nextPosition = start;
            while (nextPosition.canMove(movement)) { // 보드 끝까지 가면 끝
                nextPosition = nextPosition.move(movement); // 1칸 움직임
                if (nextPosition.equals(end)) { // 가고자 하는 위치랑 일치함
                    return movement;
                }
            }
        }
        // 일치하는거 못찾음
        throw new IllegalStateException("[ERROR] 해당하는 규칙을 찾을 수 없습니다..");
    }

    public PieceType type() {
        return pieceType;
    }

    public void recordMoved() {
        this.moved = true;
    }

    public boolean moved() {
        return moved;
    }
}
