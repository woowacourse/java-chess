package chess.domain.piece;

import chess.domain.board.Square;

public class Knight extends Piece {

    private static final int MOVABLE_STRAIGHT_DISTANCE = 2;
    private static final int MOBABLE_SIDE_DISTANCE = 1;

    public Knight(final Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        if (source.calculateRankDistance(target) == MOVABLE_STRAIGHT_DISTANCE
                && source.calculateFileDistance(target) == MOBABLE_SIDE_DISTANCE) {
            return true;
        }

        if (source.calculateRankDistance(target) == MOBABLE_SIDE_DISTANCE
                && source.calculateFileDistance(target) == MOVABLE_STRAIGHT_DISTANCE) {
            return true;
        }
        return false;
    }
}
