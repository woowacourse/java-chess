package chess.domain.policy.move;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.state.PiecesState;
import chess.domain.position.Distance;
import chess.domain.position.Position;

class CanNotReach implements CanNotMoveStrategy {
    private final double maxDistance;

    CanNotReach(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesStateO) {
        Distance distance = Distance.calculate(from, to);
        return distance.isBiggerThan(maxDistance);
    }
}
