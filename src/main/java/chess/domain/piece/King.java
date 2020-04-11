package chess.domain.piece;

import chess.domain.Team;

public class King extends Piece {

    private static final String EXPRESSION = "k";
    private static final double SCORE = 0.0;

    public King(Team team) {
        super(team, EXPRESSION, SCORE);
    }

    @Override
    public boolean canMove(MoveInformation moveInformation) {
        return moveInformation.isEndAdjacentToStart() && !moveInformation.isSameTeamPlacedOnEnd();
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
