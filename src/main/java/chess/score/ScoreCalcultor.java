package chess.score;

import chess.board.ChessBoard;
import chess.player.Player;
import chess.team.Team;

public class ScoreCalcultor implements Calculatable {
    @Override
    public Score calculate(ChessBoard chessBoard, Player player) {
        Score scoreExceptPawnReduce = player.calculateScoreExceptPawnReduce();
        Score pawnReduceScore = chessBoard.calculateReducePawnScore(player.getTeam());
        return scoreExceptPawnReduce.minus(pawnReduceScore);
    }
}
