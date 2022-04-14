package chess.service.dto;

import java.util.Map;

public class GameResultDto {
    private Map<String, Double> playerPoints;
    private String winnerColor;
    private boolean isDraw;

    public GameResultDto(Map<String, Double> playerPoints, String winnerColor, boolean isDraw) {
        this.playerPoints = playerPoints;
        this.winnerColor = winnerColor;
        this.isDraw = isDraw;
    }

    public Map<String, Double> getPlayerPoints() {
        return playerPoints;
    }

    public String getWinnerColor() {
        return winnerColor;
    }

    public boolean getIsDraw() {
        return isDraw;
    }
}
