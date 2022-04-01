package chess.dto;

import static chess.domain.Team.*;

import chess.domain.ChessBoard;
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

    public static ChessStatusDto of(ChessBoard chessBoard, Team winner) {
        Map<String, Double> teamScore = new HashMap<>();
        double blackScore = chessBoard.calculateScore(BLACK);
        teamScore.put("흑팀", blackScore);
        double whiteScore = chessBoard.calculateScore(WHITE);
        teamScore.put("백팀", whiteScore);
        return new ChessStatusDto(teamScore, winner.name());
    }

    public Map<String, Double> getTeamScore() {
        return teamScore;
    }

    public String getWinner() {
        return winner;
    }
}
