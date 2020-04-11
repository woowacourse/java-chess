package chess.domain.piece;

import chess.domain.Team;

public class Pawn extends Piece {

    private static final String EXPRESSION = "p";
    private static final double NORMAL_SCORE = 1;
    private static final double DOWNGRADED_SCORE = 0.5;

    public Pawn(Team team) {
        super(team, EXPRESSION, NORMAL_SCORE);
    }

    @Override
    public boolean canMove(MoveInformation moveInformation) {
        if (moveInformation.isEndOnDiagonalOfStart()) {
            return moveInformation.isMoveForward()
                && moveInformation.isEndAdjacentToStart()
                && moveInformation.isEnemyOnEnd();
        }
        if (moveInformation.isStraightMoveBy(1) && moveInformation.isMoveForward()) {
            return !moveInformation.doAnyPieceExistOnEnd();
        }
        /* 두 칸 전진시 */
        return moveInformation.isStraightMoveBy(2)
            && moveInformation.isStartOnInitialPosition()
            && !moveInformation.doAnyPieceExistInBetween()
            && !moveInformation.doAnyPieceExistOnEnd();
    }

    @Override
    public double getScore() {
        throw new UnsupportedOperationException("폰이 사용할 수 없는 메서드입니다.");
    }

    @Override
    public double getScore(boolean mustPawnScoreDowngraded) {
        if (mustPawnScoreDowngraded) {
            return DOWNGRADED_SCORE;
        }
        return this.score;
    }
}
