package chess.domain.piece.policy.move;

import chess.domain.board.Board;
import chess.domain.piece.position.Distance;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.piece.Initialized;

public class CanNotReach implements CanNotMoveStrategy {
    private final double maxDistance;

    public CanNotReach(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        Distance distance = initializedPiece.calculateDistance(to);
        return distance.isBiggerThan(maxDistance);
    }
}
