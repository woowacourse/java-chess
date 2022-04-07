package chess.dto;

import static chess.domain.Team.*;

import chess.domain.ChessBoard;
import chess.domain.Team;
import java.util.HashMap;
import java.util.Map;

public class ChessStatusDto {
    private static final String BLACK_TEAM_NAME = "BLACK";
    private static final String WHITE_TEAM_NAME = "WHITE";

    private final Map<String, Double> teamScore;
    private final String winner;

    private ChessStatusDto(Map<String, Double> teamScore, String winner) {
        this.teamScore = teamScore;
        this.winner = winner;
    }

    public static ChessStatusDto of(ChessBoard chessBoard, Team winner) {
        Map<String, Double> teamScore = new HashMap<>();
        double blackScore = chessBoard.calculateScore(BLACK);
        teamScore.put(BLACK_TEAM_NAME, blackScore);
        double whiteScore = chessBoard.calculateScore(WHITE);
        teamScore.put(WHITE_TEAM_NAME, whiteScore);
        return new ChessStatusDto(teamScore, winner.name());
    }

    public Map<String, Double> getTeamScore() {
        return teamScore;
    }

    public String getWinner() {
        return winner;
    }
}
