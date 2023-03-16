package chess.domain.piece.strategy;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class PawnMoveStrategy implements MoveStrategy {

    private final PawnDirection moveDirection;

    public PawnMoveStrategy(final PawnDirection moveDirection) {
        this.moveDirection = moveDirection;
    }

    @Override
    public boolean canMove(Square current, Square destination) {
        if (isDoublePawnPush(current, destination)) {
            return true;
        }

        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        return moveDirection.isExist(fileDifference, rankDifference);
    }

    private boolean isDoublePawnPush(Square current, Square destination) {
        if (moveDirection.equals(PawnDirection.UPPER)) {
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
