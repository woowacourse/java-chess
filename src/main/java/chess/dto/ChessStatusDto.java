package chess.dto;

import chess.domain.ChessBoard;
import chess.domain.Team;
import java.util.HashMap;
import java.util.Map;

public class ChessStatusDto {
    private final Map<Team, Double> teamScore;
    private final Team winner;

    private ChessStatusDto(Map<Team, Double> teamScore, Team winner) {
        this.teamScore = teamScore;
        this.winner = winner;
    }

    public static ChessStatusDto of(ChessBoard chessBoard) {
        Map<Team, Double> teamScore = new HashMap<>();
        teamScore.put(Team.BLACK, chessBoard.calculateScore(Team.BLACK));
        teamScore.put(Team.WHITE, chessBoard.calculateScore(Team.WHITE));
        Team winner = chessBoard.judgeWinner();
        return new ChessStatusDto(teamScore, winner);
    }

    public Map<Team, Double> getTeamScore() {
        return teamScore;
    }

    public Team getWinner() {
        return winner;
    }
}
