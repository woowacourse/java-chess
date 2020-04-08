package chess.score;

import chess.game.ChessSet;

public class ScoreCalculator implements Calculatable {
    private static final PawnReduceScoreCalculable pawnReduceScoreCalculable
            = new PawnReduceScoreCalculable();

    @Override
    public Score calculate(ChessSet chessSet) {
        Score scoreExceptPawnReduce = chessSet.calculateScoreExceptPawnReduce();
        Score pawnReduceScore = pawnReduceScoreCalculable.calculate(chessSet);

        return scoreExceptPawnReduce.minus(pawnReduceScore);
    }
}
