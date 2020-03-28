package chess.domain.piece.move;

import chess.domain.board.Board;
import chess.domain.piece.state.Initialized;
import chess.domain.position.Distance;
import chess.domain.position.Position;

public class CanNotReach implements CanNotMoveStrategy {
    private final int maxDistance;
    public CanNotReach(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        Distance distance = initializedPiece.calculateDistance(to);
        return distance.isBiggerThan(maxDistance);
    }
}
