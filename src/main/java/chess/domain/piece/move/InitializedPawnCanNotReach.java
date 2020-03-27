package chess.domain.piece.move;

import chess.domain.board.Board;
import chess.domain.piece.state.Initialized;
import chess.domain.position.Distance;
import chess.domain.position.Position;

public class InitializedPawnCanNotReach extends CanNotReach {
    InitializedPawnCanNotReach(int maxDistance) {
        super(maxDistance);
    }

    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        Distance distance = initializedPiece.calculateDistance(to);
        return distance.isBiggerThan(maxDistance);
    }
}
