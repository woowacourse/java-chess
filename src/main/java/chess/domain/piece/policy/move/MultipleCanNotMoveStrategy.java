package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;

import java.util.List;

public class MultipleCanNotMoveStrategy implements CanNotMoveStrategy {
    private final List<CanNotMoveStrategy> canNotMoveStrategies;

    public MultipleCanNotMoveStrategy(List<CanNotMoveStrategy> canNotMoveStrategies) {
        this.canNotMoveStrategies = canNotMoveStrategies;
    }

    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return canNotMoveStrategies.stream()
                .anyMatch(canNotMoveStrategy -> canNotMoveStrategy.canNotMove(from, to, piecesState));
    }


}
