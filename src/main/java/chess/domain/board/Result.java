package chess.domain.board;

import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

public class Result {
    private final Team winner;
    private final Score winnerScore;
    private final Team loser;
    private final Score loserScore;


    Result(Team winner, Score winnerScore, Team loser, Score loserScore) {
        this.winner = winner;
        this.winnerScore = winnerScore;
        this.loser = loser;
        this.loserScore = loserScore;
    }

    public String getWinner() {
        return winner.toString();
    }

    public String getWinnerScore() {
        return winnerScore.toString();
    }

    public String getLoser() {
        return loser.toString();
    }

    public String getLoserScore() {
        return loserScore.toString();
    }
}
