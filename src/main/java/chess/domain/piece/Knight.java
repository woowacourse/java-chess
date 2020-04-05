package chess.domain.piece;

import chess.domain.Team;

public class Knight extends Piece {

    private static final String EXPRESSION = "n";

    public Knight(Team team) {
        super(team, EXPRESSION, 2.5);
    }

    @Override
    public boolean canMove(MoveInformation moveInformation) {
        return !moveInformation.isSameTeamPlacedOnEnd()
            && moveInformation.isKnightMove();
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
