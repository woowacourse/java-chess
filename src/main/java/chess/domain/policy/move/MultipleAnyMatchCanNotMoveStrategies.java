package chess.domain.policy.move;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.state.PiecesState;
import chess.domain.position.Position;

import java.util.List;

abstract class MultipleAnyMatchCanNotMoveStrategies implements CanNotMoveStrategy {

    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        List<CanNotMoveStrategy> canNotMoveStrategies = constituteCanNotMoveStrategies(from);
        return canNotMoveStrategies.stream()
                .anyMatch(canNotMoveStrategy -> canNotMoveStrategy.canNotMove(from, to, piecesState));
    }

    protected abstract List<CanNotMoveStrategy> constituteCanNotMoveStrategies(Position position);
}
