package chess.domain.piece;

import chess.domain.board.Position;

public class Pawn extends Piece {

    private int moveCount;

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
        int sourceRankNumber = sourcePosition.getRow();
        int targetRankNumber = targetPosition.getRow();
        int direction = this.getTeam().getDirection();
        int nextRankNumber = getNextRankNumber(sourceRankNumber, direction);

        if (this.getTeam() == Team.BLACK) {
            return (isSameFileCoordinate(sourcePosition, targetPosition)
                    && nextRankNumber <= targetRankNumber && targetRankNumber < sourceRankNumber)
                    || isDiagonalPath(sourcePosition, targetPosition, team);
        }
        return (isSameFileCoordinate(sourcePosition, targetPosition)
                && sourceRankNumber < targetRankNumber && targetRankNumber <= nextRankNumber)
                || isDiagonalPath(sourcePosition, targetPosition, team);
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

        return Math.abs(sourcePosition.getColumn() - targetPosition.getColumn()) == 1
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
