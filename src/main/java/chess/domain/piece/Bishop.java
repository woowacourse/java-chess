package chess.domain.piece;

import chess.domain.Team;

public class Bishop extends Piece {

    private static final String EXPRESSION = "b";

    public Bishop(Team team) {
        super(team, EXPRESSION, 3.0);
    }

    @Override
    public boolean canMove(MoveInformation moveInformation) {
        return !moveInformation.isSameTeamPlacedOnEnd()
            && !moveInformation.doAnyPieceExistInBetween()
            && moveInformation.isEndOnDiagonalOfStart();
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
