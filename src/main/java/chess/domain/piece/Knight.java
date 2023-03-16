package chess.domain.piece;

import chess.domain.board.Square;

public class Knight extends Piece {
    private static final int MOVABLE_SIDE_DISTANCE = 1;
    private static final int MOVABLE_STRAIGHT_DISTANCE = 2;

    public Knight(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        if (source.calculateRankDistance(target) == MOVABLE_STRAIGHT_DISTANCE
                && source.calculateFileDistance(target) == MOVABLE_SIDE_DISTANCE) {
            return true;
        }

        if (source.calculateRankDistance(target) == MOVABLE_SIDE_DISTANCE
                && source.calculateFileDistance(target) == MOVABLE_STRAIGHT_DISTANCE) {
            return true;
        }
        return false;
    }
}
