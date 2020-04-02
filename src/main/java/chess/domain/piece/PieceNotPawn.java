package chess.domain.piece;

import chess.domain.Team;

public abstract class PieceNotPawn extends Piece {

    protected PieceNotPawn(Team team, String expression, double score) {
        super(team, expression, score);
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
