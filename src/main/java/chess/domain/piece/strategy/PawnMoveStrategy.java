package chess.domain.piece.strategy;

import chess.domain.square.Square;

public class PawnMoveStrategy implements MoveStrategy {

    private final PieceDirection moveDirection;

    public PawnMoveStrategy(final PieceDirection moveDirection) {
        this.moveDirection = moveDirection;
    }

    @Override
    public boolean canMove(Square current, Square destination) {
        if (isDoublePawnPush(current, destination)) {
            return true;
        }

        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        return true;
    }

    private boolean isDoublePawnPush(Square current, Square destination) {
        if (moveDirection.equals(PieceDirection.WHITE_PAWN)) {
            return isDoublePawnPushWhenUpper(current, destination);
        }
        return isDoublePawnPushWhenLower(current, destination);
    }

    private boolean isDoublePawnPushWhenUpper(final Square current, final Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        return fileDifference == 0 && rankDifference == 2 && current.isRankTwo();
    }

    private boolean isDoublePawnPushWhenLower(final Square current, final Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        return fileDifference == 0 && rankDifference == -2 && current.isRankSeven();
    }
}
