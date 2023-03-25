package chess.domain.piece;

import chess.domain.board.Square;

public class Pawn extends Piece {

    private static final int WHITE_MAX_MOVABLE_RANK = 2;
    private static final int WHITE_MIN_MOVABLE_RANK = 1;
    private static final int BLACK_MAX_MOVABLE_RANK = -2;
    private static final int BLACK_MIN_MOVABLE_RANK = -1;
    private static final int SELF_SQUARE = 0;
    private static final int NEXT_SQUARE = 1;

    public Pawn(final Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean canMove(final Square source, final Square target) {

        if (isWhite()) {
            return canMoveWhite(source, target);
        }

        return canMoveBlack(source, target);
    }

    private boolean canMoveBlack(final Square source, final Square target) {
        if (source.calculateRankDifference(target) >= SELF_SQUARE) {
            return false;
        }
        return canBlackFirstMove(source, target);
    }

    private boolean canMoveWhite(final Square source, final Square target) {

        if (source.calculateRankDifference(target) <= SELF_SQUARE) {
            return false;
        }
        return canWhiteFirstMove(source, target);
    }

    private boolean canBlackFirstMove(final Square source, final Square target) {
        final int rankDifference = source.calculateRankDifference(target);
        final int fileDistance = source.calculateFileDistance(target);

        if (source.isRankSeven()) {
            return rankDifference >= BLACK_MAX_MOVABLE_RANK && fileDistance == SELF_SQUARE ||
                    rankDifference == BLACK_MIN_MOVABLE_RANK && fileDistance == NEXT_SQUARE;
        }

        return rankDifference == BLACK_MIN_MOVABLE_RANK && fileDistance <= NEXT_SQUARE;
    }

    private static boolean canWhiteFirstMove(final Square source, final Square target) {
        final int rankDifference = source.calculateRankDifference(target);
        final int fileDistance = source.calculateFileDistance(target);

        if (source.isRankTwo()) {
            return rankDifference <= WHITE_MAX_MOVABLE_RANK && fileDistance == SELF_SQUARE ||
                    rankDifference == WHITE_MIN_MOVABLE_RANK && fileDistance == NEXT_SQUARE;
        }

        return rankDifference == WHITE_MIN_MOVABLE_RANK && fileDistance <= NEXT_SQUARE;
    }
}
