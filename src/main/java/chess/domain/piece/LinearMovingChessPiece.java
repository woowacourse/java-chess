package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import java.util.Collections;
import java.util.List;

public abstract class LinearMovingChessPiece implements ChessPiece {

    protected final List<Movement> directions;

    public LinearMovingChessPiece(List<Movement> directions) {
        this.directions = directions;
    }

    @Override
    public void validateCanMove(Position origin, Position destination) {
        boolean canMove = false;
        for (Movement direction : directions) {
            Position origin2 = origin;
            while (origin2.canMove(direction)) {
                origin2 = origin2.move(direction);
                if (origin2.equals(destination)) {
                    canMove = true;
                    break;
                }
            }
        }
        if (!canMove) {
            throw new IllegalStateException("해당 기물은 해당 경로로 이동할 수 없습니다.");
        }
    }

    @Override
    public List<Movement> findRoute(Position origin, Position destination) {
        for (Movement direction : directions) {
            Position origin2 = origin;
            int i = 0;
            while (origin2.canMove(direction)) {
                origin2 = origin2.move(direction);
                i++;
                if (origin2.equals(destination)) {
                    return Collections.nCopies(i, direction);
                }
            }
        }
        return List.of();
    }

    @Override
    public void move() {

    }
}
