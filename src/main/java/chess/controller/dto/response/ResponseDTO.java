package chess.controller.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseDTO {
    private final List<String> cellsStatus;
    private final String currentTurnTeamName;
    private final double blackPlayerScore;
    private final double whitePlayerScore;
    private final boolean isKingDead;
    private final String winnerName;

    public ResponseDTO(List<String> cellsStatus, String currentTurnTeamName,
        double blackPlayerScore, double whitePlayerScore, boolean isKingDead, String winnerName) {

        this.cellsStatus = new ArrayList<>(cellsStatus);
        this.currentTurnTeamName = currentTurnTeamName;
        this.blackPlayerScore = blackPlayerScore;
        this.whitePlayerScore = whitePlayerScore;
        this.isKingDead = isKingDead;
        this.winnerName = winnerName;
    }

    public List<String> getCellsStatus() {
        return cellsStatus;
    }

    public String getCurrentTurnTeamName() {
        return currentTurnTeamName;
    }

    public double getBlackPlayerScore() {
        return blackPlayerScore;
    }

    public double getWhitePlayerScore() {
        return whitePlayerScore;
    }

    public boolean getIsKingDead() {
        return isKingDead;
    }

    public String getWinnerName() {
        return winnerName;
    }
}
