package chess.domain.piece;

import chess.domain.square.Square;

public class Bishop extends Piece {

    public Bishop(PieceColor color) {
        super(PieceType.BISHOP, color);
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return (source.calculateRankDiff(target.rank()) ==
                source.calculateFileDiff(target.file()));
    }
}
