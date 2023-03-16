package chess.domain.piece;

import chess.domain.board.Square;

public class Queen extends Piece {
    public Queen(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return isStraight(source, target) || isDiagonal(source, target);
    }

    private boolean isDiagonal(Square source, Square target) {
        return source.calculateFileDistance(target) == source.calculateRankDistance(target);
    }

    private boolean isStraight(Square source, Square target) {
        return source.isSameFile(target) || source.isSameRank(target);
    }
}
