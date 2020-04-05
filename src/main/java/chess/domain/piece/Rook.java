package chess.domain.piece;

import chess.domain.Team;

public class Rook extends Piece {

    private static final String EXPRESSION = "r";
    private static final double SCORE = 5.0;

    public Rook(Team team) {
        super(team, EXPRESSION, SCORE);
    }

    @Override
    public boolean canMove(MoveInformation moveInformation) {
        return !moveInformation.isSameTeamPlacedOnEnd()
            && moveInformation.isEndOnStraightLineOfStart()
            && !moveInformation.doAnyPieceExistInBetween();
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
