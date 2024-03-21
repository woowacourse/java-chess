package chess.domain.piece;

import chess.domain.square.Square;

public class Queen extends Piece {

    public Queen(Team color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return (source.isSameRank(target) || source.isSameFile(target)) ||
                (source.calculateRankDiff(target.rank()) ==
                        source.calculateFileDiff(target.file()));
    }
}
