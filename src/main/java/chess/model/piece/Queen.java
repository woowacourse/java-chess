package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Distance;
import java.util.List;

public class Queen extends Piece {
    public Queen(Side side) {
        super(side);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "q";
        }
        return "Q";
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        checkValidTargetPiece(targetPiece);
        Distance distance = target.calculateDistance(source);
        if (canMove(distance)) {
            return distance.findPath(source);
        }
        throw new IllegalStateException("퀸은 해당 경로로 이동할 수 없습니다.");
    }

    private boolean canMove(Distance distance) {
        return distance.isCrossMovement() || distance.isDiagonalMovement();
    }
}
