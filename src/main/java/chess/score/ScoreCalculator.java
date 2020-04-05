package chess.score;

import chess.board.ChessBoard;
import chess.player.Player;

public class ScoreCalculator implements Calculatable {
    @Override
    public Score calculate(ChessBoard chessBoard, Player player) {
        Score scoreExceptPawnReduce = player.calculateScoreExceptPawnReduce();
        Score pawnReduceScore = chessBoard.calculateReducePawnScore(player);
        return scoreExceptPawnReduce.minus(pawnReduceScore);
    }
}
