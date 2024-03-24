package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Distance;
import java.util.List;

public class King extends Piece {
    private static final int DISPLACEMENT = 1;

    public King(Side side) {
        super(side);
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        checkValidTargetPiece(targetPiece);
        Distance distance = target.calculateDistance(source);
        if (distance.hasSame(DISPLACEMENT)) {
            return List.of(target);
        }
        throw new IllegalStateException("왕은 해당 경로로 이동할 수 없습니다.");
    }
}
