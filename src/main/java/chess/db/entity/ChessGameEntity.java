package chess.db.entity;

import static chess.beforedb.domain.player.type.TeamColor.WHITE;

import chess.beforedb.domain.player.type.TeamColor;
import java.util.Objects;

public class ChessGameEntity {
    private Long id;
    private final String title;
    private TeamColor currentTurnTeamColor;

    public ChessGameEntity(String title) {
        this.currentTurnTeamColor = WHITE;
        if (title.isEmpty()) {
            this.title = "제목 없는 방";
            return;
        }
        this.title = title;
    }

    public ChessGameEntity(Long id, String title, String currentTurnTeamColor) {
        this.id = id;
        this.title = title;
        this.currentTurnTeamColor = TeamColor.of(currentTurnTeamColor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public TeamColor getCurrentTurnTeamColor() {
        return currentTurnTeamColor;
    }

    public void setCurrentTurnTeamColor(TeamColor currentTurnTeamColor) {
        this.currentTurnTeamColor = currentTurnTeamColor;
    }

    public void addPlayer(PlayerEntity playerEntity) {
        playerEntity.setChessGameEntity(this);
    }

    public String getCurrentTurnTeamColorName() {
        return currentTurnTeamColor.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChessGameEntity)) {
            return false;
        }
        ChessGameEntity that = (ChessGameEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public String getOppositeTeamColorName() {
        return currentTurnTeamColor.oppositeTeamColorName();
    }
}
