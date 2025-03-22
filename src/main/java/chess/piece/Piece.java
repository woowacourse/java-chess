package chess.piece;

import chess.position.Movement;
import chess.position.Position;
import java.util.List;

public abstract class Piece {
    private final List<Movement> movements;
    private final PieceType pieceType;

    public Piece(List<Movement> movements, PieceType pieceType) {
        this.movements = movements;
        this.pieceType = pieceType;
    }

    public PieceType type() {
        return pieceType;
    }

    public void validateMove(Position start, Position end) {
        for (Movement movement : movements) {
            Position nextPosition = start.move(movement);
            if (nextPosition.equals(end)) { // 일치하는게 있음
                return;
            }
        }
        // 일치하는거 못찾음
        throw new IllegalArgumentException("[ERROR] 기물의 이동 규칙에 어긋나는 움직임입니다.");
    }
}
