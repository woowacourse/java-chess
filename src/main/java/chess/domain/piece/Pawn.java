package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import java.util.Collections;
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
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();
        int direction = this.getColor().getDirection();
        int nextRankNumber = sourceRankNumber + direction;

        boolean diagonalPath = isDiagonalPath(sourcePosition, targetPosition);
        if (isFirstMove()) {
            nextRankNumber = sourceRankNumber + (2 * direction);
        }
        return (isSameFileCoordinate && sourceRankNumber < targetRankNumber && targetRankNumber <= nextRankNumber)
                || diagonalPath;
    }

    private boolean isDiagonalPath(Position sourcePosition, Position targetPosition) {
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();
        int direction = this.getColor().getDirection();
        int diagonalRankNumber = sourceRankNumber + direction;
        int sourceFileNumber = sourcePosition.getColumn();
        int targetFileNumber = targetPosition.getColumn();

        return Math.abs(sourceFileNumber - targetFileNumber) == 1 && diagonalRankNumber == targetRankNumber;
    }

    @Override
    List<Position> findPath(Position sourcePosition, Position targetPosition) {
        if (!canMove(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();
        if (Math.abs(sourceRankNumber - targetRankNumber) == 2) {
            int direction = this.getColor().getDirection();
            return List.of(new Position(sourcePosition.getFileCoordinate(),
                    RankCoordinate.findBy(sourceRankNumber + direction)));
        }
        return Collections.emptyList();
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
