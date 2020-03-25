package chess.domain.pieceRule;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RookRule implements PieceRule {
    @Override
    public Optional<MoveDirection> canMoveDirection(Position sourcePosition, Position targetPosition) {
        List<MoveDirection> moveDirections = Arrays.asList(
                MoveDirection.N,
                MoveDirection.E,
                MoveDirection.S,
                MoveDirection.W);

        return moveDirections.stream()
                .filter(moveDirection -> moveDirection.isSameDirection(sourcePosition, targetPosition))
                .findAny();
    }

    @Override
    public boolean canMoveRange(Position sourcePosition, Position targetPosition) {
        return false;
    }
}
