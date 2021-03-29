package chess.dao.entity;

import chess.domain.player.type.TeamColor;

public class GameStatusEntity {
    private final String title;
    private final TeamColor currentTurnTeamColor;

    public GameStatusEntity(String title, String currentTurnTeamColorValue) {
        this.title = title;
        this.currentTurnTeamColor = TeamColor.of(currentTurnTeamColorValue);
    }

    public String getTitle() {
        return title;
    }

    public TeamColor getCurrentTurnTeamColor() {
        return currentTurnTeamColor;
    }
}
