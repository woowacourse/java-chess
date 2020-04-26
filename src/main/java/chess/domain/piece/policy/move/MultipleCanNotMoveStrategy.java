package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;

import java.util.List;

public abstract class MultipleCanNotMoveStrategy implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        List<CanNotMoveStrategy> canNotMoveStrategies = constitueStrategies();
        return canNotMoveStrategies.stream()
                .anyMatch(canNotMoveStrategy -> canNotMoveStrategy.canNotMove(from, to, piecesState));
    }

    protected abstract List<CanNotMoveStrategy> constitueStrategies();
}
