package chess.dto;

import chess.domain.board.Color;
import chess.domain.position.Result;
import java.util.Map;

public class StatusDto {

    private final double blackScore;
    private final double whiteScore;
    private final String winningTeam;

    private StatusDto(double blackScore, double whiteScore, String winningTeam) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
        this.winningTeam = winningTeam;
    }

    public static StatusDto from(Map<Color, Double> scoreByColor, Result result) {
        return new StatusDto(scoreByColor.get(Color.BLACK), scoreByColor.get(Color.WHITE), result.name());
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public String getWinningTeam() {
        return winningTeam;
    }
}
