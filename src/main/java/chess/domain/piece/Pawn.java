package chess.domain.piece;

import chess.domain.board.Square;

public class Pawn extends Piece {
    private static final Pawn WHITE_PAWN = new Pawn(Camp.WHITE);
    private static final Pawn BLACK_PAWN = new Pawn(Camp.BLACK);

    private static final int WHITE_MAX_MOVABLE_RANK = 2;
    private static final int WHITE_MIN_MOVABLE_RANK = 1;
    private static final int BLACK_MAX_MOVABLE_RANK = -2;
    private static final int BLACK_MIN_MOVABLE_RANK = -1;
    private static final int MIN_MOVABLE_FILE = 0;
    private static final int MAX_MOVABLE_FILE = 1;

    private Pawn(Camp camp) {
        super(camp);
    }

    public static Pawn getInstanceOf(Camp camp) {
        if (camp == Camp.WHITE) {
            return WHITE_PAWN;
        }

        return BLACK_PAWN;
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

        if (source.isWhitePawnInitialRank()) {
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

        if (source.isBlackPawnInitialRank()) {
            return rankDifference >= BLACK_MAX_MOVABLE_RANK && fileDistance == MIN_MOVABLE_FILE ||
                    rankDifference == BLACK_MIN_MOVABLE_RANK && fileDistance == MAX_MOVABLE_FILE;
        }

        return rankDifference == BLACK_MIN_MOVABLE_RANK && fileDistance <= MAX_MOVABLE_FILE;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }
}
