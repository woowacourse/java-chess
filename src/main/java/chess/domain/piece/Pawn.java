package chess.domain.piece;

import chess.domain.board.Position;

public class Pawn extends Piece {

    private static final int INIT_MOVE_COUNT = 0;
    private static final int FIRST_STEP = 2;
    private static final int ONE_DIFFERENCE = 1;
    private static final int MOVE_COUNT_STEP = 1;

    private final int moveCount;

    public Pawn(Team team, Position position) {
        this(team, position, INIT_MOVE_COUNT);
    }

    private Pawn(Team team, Position position, int moveCount) {
        super(team, position, PieceType.PAWN);
        this.moveCount = moveCount;
    }

    private boolean isFirstMove() {
        return this.moveCount == 0;
    }

    @Override
    public boolean canMove(Position targetPosition, Team team) {
        return isStraightPath(targetPosition, team)
                || isDiagonalPath(targetPosition, team);
    }

    public boolean isStraightPath(Position targetPosition, Team targetTeam) {
        int sourceRankNumber = position.getRow();
        int targetRankNumber = targetPosition.getRow();
        int nextRankNumber = getNextRankNumber(sourceRankNumber, team.getDirection());
        if (team == Team.BLACK) {
            return isSameFileCoordinate(targetPosition)
                    && nextRankNumber <= targetRankNumber && targetRankNumber < sourceRankNumber
                    && targetTeam == Team.EMPTY;
        }
        return isSameFileCoordinate(targetPosition)
                && sourceRankNumber < targetRankNumber && targetRankNumber <= nextRankNumber
                && targetTeam == Team.EMPTY;
    }

    private int getNextRankNumber(int sourceRankNumber, int direction) {
        if (isFirstMove()) {
            return sourceRankNumber + (FIRST_STEP * direction);
        }
        return sourceRankNumber + direction;
    }

    private boolean isSameFileCoordinate(Position targetPosition) {
        return position.getFileCoordinate() == targetPosition.getFileCoordinate();
    }

    private boolean isDiagonalPath(Position targetPosition, Team targetTeam) {
        if (!team.isOpposite(targetTeam)) {
            return false;
        }

        int diagonalRankNumber = position.getRow() + team.getDirection();

        return position.calculateColumnDistance(targetPosition) == ONE_DIFFERENCE
                && diagonalRankNumber == targetPosition.getRow()
                && isDifferentTeam(targetTeam);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move(Position targetPosition, Team nowPlayingTeam, Team targetTeam) {
        validate(targetPosition, nowPlayingTeam, targetTeam);
        return new Pawn(team, targetPosition, moveCount + MOVE_COUNT_STEP);
    }
}
