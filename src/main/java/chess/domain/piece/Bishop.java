package chess.domain.piece;

import chess.domain.board.Square;

public class Bishop extends Piece {
    public Bishop(final Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        return source.calculateFileDistance(target)
                == source.calculateRankDistance(target);
    }
}
