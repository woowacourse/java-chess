package chess.piece;

import chess.Square;

public class Bishop extends Piece {
    public Bishop(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return source.calculateFileDistance(target) == source.calculateRankDistance(target);
    }
}
