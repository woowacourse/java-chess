package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Distance;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Side side) {
        super(side);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "b";
        }
        return "B";
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        checkValidTargetPiece(targetPiece);
        Distance distance = target.calculateDistance(source);
        if (distance.isDiagonalMovement()) {
            return distance.findPath(source);
        }
        throw new IllegalStateException("비숍은 해당 경로로 이동할 수 없습니다.");
    }
}
