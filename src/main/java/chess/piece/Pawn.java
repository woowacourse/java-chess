package chess.piece;

import chess.Square;

public class Pawn extends Piece {

    private static final int WHITE_MAX_MOVABLE_RANK = 2;
    private static final int WHITE_MIN_MOVABLE_RANK = 1;
    private static final int BLACK_MAX_MOVABLE_RANK = -2;
    private static final int BACK_MIN_MOVABLE_RANK = -1;
    private static final int MIN_MOVABLE_FILE = 1;

    public Pawn(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        int rankDifference = source.calculateRankDifference(target);
        int fileDistance = source.calculateFileDistance(target);

        if (isWhite()) {
            return canMoveWhite(rankDifference, fileDistance);
        }

        return canMoveBlack(rankDifference, fileDistance);
    }

    private boolean canMoveBlack(int rankDifference, int fileDistance) {
        if (rankDifference < BLACK_MAX_MOVABLE_RANK || rankDifference > BACK_MIN_MOVABLE_RANK) {
            return false;
        }
        if (fileDistance > MIN_MOVABLE_FILE) {
            return false;
        }
        return true;
    }

    private boolean canMoveWhite(int rankDifference, int fileDistance) {
        if (rankDifference > WHITE_MAX_MOVABLE_RANK || rankDifference < WHITE_MIN_MOVABLE_RANK) {
            return false;
        }
        if (fileDistance > MIN_MOVABLE_FILE) {
            return false;
        }
        return true;
    }
}
