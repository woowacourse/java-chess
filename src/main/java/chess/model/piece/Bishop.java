package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Distance;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(final Side side) {
        super(side);
    }

    @Override
    public List<ChessPosition> findPath(
            final ChessPosition source, final ChessPosition target, final Piece targetPiece
    ) {
        checkValidTargetPiece(targetPiece);
        Distance distance = target.calculateDistance(source);
        if (distance.isDiagonalMovement()) {
            return distance.findPath(source);
        }
        throw new IllegalStateException("비숍은 해당 경로로 이동할 수 없습니다.");
    }
}
