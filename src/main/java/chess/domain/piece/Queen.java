package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Square;

public class Queen extends Piece {

    public Queen(PieceColor color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return (source.isSameRank(target) || source.isSameFile(target)) ||
                (source.calculateRankDiff(target.rank()) ==
                        source.calculateFileDiff(target.file()));
    }
}
