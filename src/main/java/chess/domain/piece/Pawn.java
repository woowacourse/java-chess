package chess.domain.piece;

import chess.domain.board.Position;

public class Pawn extends Piece {

    private final int moveCount;

    public Pawn(Team team) {
        this(team, 0);
    }

    private Pawn(Team team, int moveCount) {
        super(team);
        this.moveCount = moveCount;
    }

    private boolean isFirstMove() {
        return this.moveCount == 0;
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Team team) {
        return isStraightPath(sourcePosition, targetPosition, team)
                || isDiagonalPath(sourcePosition, targetPosition, team);
    }

    public boolean isStraightPath(Position sourcePosition, Position targetPosition, Team team) {
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();
        int nextRankNumber = getNextRankNumber(sourceRankNumber, getTeam().getDirection());
        if (getTeam() == Team.BLACK) {
            return isSameFileCoordinate(sourcePosition, targetPosition)
                    && nextRankNumber <= targetRankNumber && targetRankNumber < sourceRankNumber
                    && team == Team.EMPTY;
        }
        return isSameFileCoordinate(sourcePosition, targetPosition)
                && sourceRankNumber < targetRankNumber && targetRankNumber <= nextRankNumber
                && team == Team.EMPTY;
    }

    private int getNextRankNumber(int sourceRankNumber, int direction) {
        if (isFirstMove()) {
            return sourceRankNumber + (2 * direction);
        }
        return sourceRankNumber + direction;
    }

    private boolean isSameFileCoordinate(Position sourcePosition, Position targetPosition) {
        return sourcePosition.getFileCoordinate() == targetPosition.getFileCoordinate();
    }

    private boolean isDiagonalPath(Position sourcePosition, Position targetPosition, Team team) {
        if (!this.getTeam().isOpposite(team)) {
            return false;
        }

        int diagonalRankNumber = sourcePosition.getRow() + this.getTeam().getDirection();

        return sourcePosition.calculateRowDifferenceWith(targetPosition) == 1
                && diagonalRankNumber == targetPosition.getRow()
                && isNotSameTeam(team);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move() {
        return new Pawn(this.getTeam(), moveCount + 1);
    }
}
