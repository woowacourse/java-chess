package chess.db.entity;

import static chess.domain.player.type.TeamColor.WHITE;

import java.util.Objects;

public class ChessGameEntity {
    private Long id;
    private String title;
    private String currentTeamColor;

    public ChessGameEntity(String title) {
        this.title = title;
        this.currentTeamColor = WHITE.getValue();
    }

    public ChessGameEntity(Long id, String title, String current_turn_team_color) {
        this.id = id;
        this.title = title;
        this.currentTeamColor = current_turn_team_color;
    }

    public ChessGameEntity(String title, String currentTurnTeamColor) {
        this.title = title;
        this.currentTeamColor = currentTurnTeamColor;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrentTeamColor() {
        return currentTeamColor;
    }

    public void setCurrentTeamColor(String currentTeamColor) {
        this.currentTeamColor = currentTeamColor;
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

    public void addPlayer(PlayerEntity playerEntity) {
        playerEntity.setChessGameEntity(this);
    }
}
