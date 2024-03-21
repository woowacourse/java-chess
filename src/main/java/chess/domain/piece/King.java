package chess.domain.piece;

import chess.domain.square.Square;

public class King extends Piece {

    public King(PieceColor color) {
        super(PieceType.KING, color);
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return (source.calculateRankDiff(target.rank()) <= 1 &&
                source.calculateFileDiff(target.file()) <= 1);
    }
}
