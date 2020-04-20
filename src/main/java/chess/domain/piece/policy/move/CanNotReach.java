package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Distance;
import chess.domain.piece.position.Position;

public class CanNotReach implements CanNotMoveStrategy {
    private final double maxDistance;

    public CanNotReach(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    //포지션 받는 게 나음
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesStateO) {
        Distance distance = Distance.calculate(from, to);
        return distance.isBiggerThan(maxDistance);
    }
}
