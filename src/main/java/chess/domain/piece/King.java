package chess.domain.piece;

import chess.domain.board.Square;

public class King extends Piece {
    private static final int MOVABLE_DISTANCE = 1;

    public King(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return (source.calculateRankDistance(target) == MOVABLE_DISTANCE) ||
                (source.calculateFileDistance(target) == MOVABLE_DISTANCE);
    }
}
