package chess.db.domain.game;

import chess.beforedb.domain.player.type.TeamColor;

public class GameStatusResponseDTO {
    private final String title;
    private final String currentTurnTeamColorName;
    private final double blackPlayerScore;
    private final double whitePlayerScore;

    public GameStatusResponseDTO(String title, TeamColor teamColor,
        double blackPlayerScore, double whitePlayerScore) {

        this.title = title;
        this.currentTurnTeamColorName = teamColor.getName();
        this.blackPlayerScore = blackPlayerScore;
        this.whitePlayerScore = whitePlayerScore;
    }

    public String getTitle() {
        return title;
    }

    public String getCurrentTurnTeamColorName() {
        return currentTurnTeamColorName;
    }

    public double getBlackPlayerScore() {
        return blackPlayerScore;
    }

    public double getWhitePlayerScore() {
        return whitePlayerScore;
    }
}
