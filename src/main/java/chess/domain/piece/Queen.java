package chess.domain.piece;

import chess.domain.Team;

public class Queen extends Piece {

    private static final String EXPRESSION = "q";
    private static final double SCORE = 9.0;

    public Queen(Team team) {
        super(team, EXPRESSION, SCORE);
    }

    @Override
    public boolean canMove(MoveInformation moveInformation) {
        return !moveInformation.isSameTeamPlacedOnEnd()
            && !moveInformation.doAnyPieceExistInBetween()
            && (moveInformation.isEndOnDiagonalOfStart()
            || moveInformation.isEndOnStraightLineOfStart());
    }

    @Override
    public double getScore() {
        return this.score;
    }

    @Override
    public double getScore(boolean mustPawnScoreChangeToHalf) {
        throw new UnsupportedOperationException("폰만 사용할 수 있는 메서드입니다.");
    }
}
