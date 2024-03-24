package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Distance;
import java.util.List;

public class Knight extends Piece {
    private static final int DISPLACEMENT = 3;

    public Knight(Side side) {
        super(side);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "n";
        }
        return "N";
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        checkValidTargetPiece(targetPiece);
        Distance distance = target.calculateDistance(source);
        if (canMove(distance)) {
            return List.of(target);
        }
        throw new IllegalStateException("나이트는 해당 경로로 이동할 수 없습니다.");
    }

    private boolean canMove(Distance distance) {
        return distance.hasSame(DISPLACEMENT)
                && !distance.isCrossMovement()
                && !distance.isDiagonalMovement();
    }
}
