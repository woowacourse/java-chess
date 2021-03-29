package chess.db.domain.game;

import chess.beforedb.domain.player.type.TeamColor;

public class GameStatusResponseDTO {
    private final String title;
    private final String currentTurnTeamColorName;
    private final String beforeTurnTeamColorName;
    private final double whitePlayerScore;
    private final double blackPlayerScore;

    public GameStatusResponseDTO(String title, TeamColor teamColor,
        double whitePlayerScore, double blackPlayerScore) {

        this.title = title;
        this.currentTurnTeamColorName = teamColor.getName();
        this.beforeTurnTeamColorName = teamColor.oppositeTeamColorName();
        this.whitePlayerScore = whitePlayerScore;
        this.blackPlayerScore = blackPlayerScore;
    }

    public String getTitle() {
        return title;
    }

    public String getCurrentTurnTeamColorName() {
        return currentTurnTeamColorName;
    }

    public String getBeforeTurnTeamColorName() {
        return beforeTurnTeamColorName;
    }

    public double getWhitePlayerScore() {
        return whitePlayerScore;
    }

    public double getBlackPlayerScore() {
        return blackPlayerScore;
    }
}
