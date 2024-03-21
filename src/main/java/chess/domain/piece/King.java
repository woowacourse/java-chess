package chess.domain.piece;

import chess.domain.square.Square;

public class King extends Piece {

    private static final int STEP_LIMIT = 1;

    public King(PieceColor color) {
        super(PieceType.KING, color);
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return (source.calculateRankDiff(target.rank()) <= STEP_LIMIT &&
                source.calculateFileDiff(target.file()) <= STEP_LIMIT);
    }
}
