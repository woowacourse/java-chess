package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import java.util.List;

public abstract class LimitedMovingChessPiece implements ChessPiece {

    protected final List<Movement> movements;

    public LimitedMovingChessPiece(List<Movement> movements) {
        this.movements = movements;
    }

    @Override
    public void validateCanMove(Position origin, Position destination) {
        boolean canMove = false;
        for (Movement movement : movements) {
            if (origin.move(movement).equals(destination)) {
                canMove = true;
                break;
            }
        }
        if (!canMove) {
            throw new IllegalStateException("해당 기물은 해당 경로로 이동할 수 없습니다.");
        }
    }

    @Override
    public List<Movement> findRoute(Position origin, Position destination) {
        for (Movement movement : movements) {
            if (origin.move(movement).equals(destination)) {
                return List.of(movement);
            }
        }
        return List.of();
    }

    @Override
    public void move() {

    }
}
