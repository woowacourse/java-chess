/*
package chess.dto;

import chess.domain.ChessGame;
import chess.domain.Team;
import java.util.HashMap;
import java.util.Map;

public class ChessStatusDto {
    private final Map<String, Double> teamScore;
    private final String winner;

    private ChessStatusDto(Map<String, Double> teamScore, String winner) {
        this.teamScore = teamScore;
        this.winner = winner;
    }

    public static ChessStatusDto of(ChessGame chessGame) {
        Map<String, Double> teamScore = new HashMap<>();
        teamScore.put("흑팀", chessGame.getWhiteChessMen().calculateScore());
        teamScore.put("백팀", chessGame.getBlackChessMen().calculateScore());
        Team winner = chessGame.judgeWinner();
        return new ChessStatusDto(teamScore, winner.name());
    }

    public Map<String, Double> getTeamScore() {
        return teamScore;
    }

    public String getWinner() {
        return winner;
    }
}
*/
