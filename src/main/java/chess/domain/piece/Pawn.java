package chess.domain.piece;

import chess.domain.board.Position;
import java.util.List;

public class Pawn extends Piece {

    private int moveCount;

    public Pawn(Color color) {
        super(color);
    }

    private boolean isFirstMove() {
        return this.moveCount == 0;
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        boolean isSameFileCoordinate = sourcePosition.getFileCoordinate() == targetPosition.getFileCoordinate();
        int sourceRankNumber = sourcePosition.getRankCoordinate().getRowNumber();
        int targetRankNumber = targetPosition.getRankCoordinate().getRowNumber();
        int direction = this.getColor().getDirection();
        int nextRankNumber = sourceRankNumber + direction;

        if (isFirstMove()) {
            nextRankNumber = sourceRankNumber + (2 * direction);
            return isSameFileCoordinate && sourceRankNumber < targetRankNumber && targetRankNumber <= nextRankNumber;
        }
        return isSameFileCoordinate && sourceRankNumber < targetRankNumber && targetRankNumber <= nextRankNumber;
    }

    @Override
    List<Position> findPath(Position sourcePosition, Position targetPosition) {
        return null;
    }

    @Override
    boolean isKing() {
        return false;
    }

    @Override
    Piece move() {
        return null;
    }
}
