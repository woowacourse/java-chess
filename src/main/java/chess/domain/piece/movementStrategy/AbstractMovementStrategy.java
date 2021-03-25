package chess.domain.piece.movementStrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;
import java.util.List;

public abstract class AbstractMovementStrategy implements MovementStrategy {

    @Override
    public MoveVector movableVector(Point source, Point destination) {
        int x = destination.columnDifference(source);
        int y = destination.rowDifference(source);

        return possibleMoveVectors().stream()
            .filter(moveVector -> moveVector.isSameDirection(x, y))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("이동할 수 있는 방향이 아닙니다."));
    }

    @Override
    public boolean hasMovableVector(Point source, Point destination) {
        int x = destination.columnDifference(source);
        int y = destination.rowDifference(source);

        return possibleMoveVectors().stream()
            .anyMatch(moveVector -> moveVector.isSameDirection(x, y));
    }

    protected abstract List<MoveVector> possibleMoveVectors();
}
