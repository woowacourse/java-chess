package chess.domain.board;

import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return winner == result.winner &&
                Objects.equals(winnerScore, result.winnerScore) &&
                loser == result.loser &&
                Objects.equals(loserScore, result.loserScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winner, winnerScore, loser, loserScore);
    }
}
