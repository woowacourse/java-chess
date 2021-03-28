package chess.db.domain.game;

import chess.beforedb.domain.player.type.TeamColor;

public class GameStatusEntity {
    private final String title;
    private final TeamColor currentTurnTeamColor;
    private final double blackPlayerScore;
    private final double whitePlayerScore;

    public GameStatusEntity(String title, String currentTurnTeamColorValue, double blackPlayerScore,
        double whitePlayerScore) {

        this.title = title;
        this.currentTurnTeamColor = TeamColor.of(currentTurnTeamColorValue);
        this.blackPlayerScore = blackPlayerScore;
        this.whitePlayerScore = whitePlayerScore;
    }

    public String getTitle() {
        return title;
    }

    public TeamColor getCurrentTurnTeamColor() {
        return currentTurnTeamColor;
    }

    public double getBlackPlayerScore() {
        return blackPlayerScore;
    }

    public double getWhitePlayerScore() {
        return whitePlayerScore;
    }
}
