package chess.domain.piece;

import chess.domain.board.Square;

public class Pawn extends Piece {

    private static final int WHITE_MAX_MOVABLE_RANK = 2;
    private static final int WHITE_MIN_MOVABLE_RANK = 1;
    private static final int BLACK_MAX_MOVABLE_RANK = -2;
    private static final int BLACK_MIN_MOVABLE_RANK = -1;
    private static final int MIN_MOVABLE_FILE = 0;
    private static final int MAX_MOVABLE_FILE = 1;


    public Pawn(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean canMove(Square source, Square target) {

        if (isWhite()) {
            return canMoveWhite(source, target);
        }

        return canMoveBlack(source, target);
    }

    private boolean canMoveWhite(Square source, Square target) {
        int rankDifference = source.calculateRankDifference(target);
        int fileDistance = source.calculateFileDistance(target);

        if (rankDifference < WHITE_MIN_MOVABLE_RANK) {
            return false;
        }

        if (source.isRankTwo()) {
            return rankDifference <= WHITE_MAX_MOVABLE_RANK && fileDistance == MIN_MOVABLE_FILE ||
                    rankDifference == WHITE_MIN_MOVABLE_RANK && fileDistance == MAX_MOVABLE_FILE;
        }

        return rankDifference == WHITE_MIN_MOVABLE_RANK && fileDistance <= MAX_MOVABLE_FILE;
    }

    private boolean canMoveBlack(Square source, Square target) {
        int rankDifference = source.calculateRankDifference(target);
        int fileDistance = source.calculateFileDistance(target);

        if (rankDifference > BLACK_MIN_MOVABLE_RANK) {
            return false;
        }

        if (source.isRankSeven()) {
            return rankDifference >= BLACK_MAX_MOVABLE_RANK && fileDistance == MIN_MOVABLE_FILE ||
                    rankDifference == BLACK_MIN_MOVABLE_RANK && fileDistance == MAX_MOVABLE_FILE;
        }

        return rankDifference == BLACK_MIN_MOVABLE_RANK && fileDistance <= MAX_MOVABLE_FILE;
    }
}
