package domain.piece.score;

import java.util.List;

import domain.piece.Piece;

public class ScoreCalculator {

    public static final double PAWN_PENALTY_SCORE = -0.5;

    public static Score calculateSum(List<Piece> pieces) {
        return pieces.stream()
                .map(Piece::getScore)
                .reduce(Score.ZERO_SCORE, Score::add);
    }

    public static Score calculateScoreWithPawnCount(Score score, int pawnCount) {
        double pawnPenalty = pawnCount * PAWN_PENALTY_SCORE;
        return score.add(new Score(pawnPenalty));
    }
}
