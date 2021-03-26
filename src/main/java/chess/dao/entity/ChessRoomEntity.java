package chess.dao.entity;

import static chess.domain.player.type.TeamColor.WHITE;

import java.util.Objects;

public class ChessRoomEntity {
    private Long id;
    private String title;
    private String currentTeamColor;

    public ChessRoomEntity(String title) {
        this.title = title;
        this.currentTeamColor = WHITE.getValue();
    }

    public ChessRoomEntity(Long id, String title, String current_turn_team_color) {
        this.id = id;
        this.title = title;
        this.currentTeamColor = current_turn_team_color;
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
        if (!(o instanceof ChessRoomEntity)) {
            return false;
        }
        ChessRoomEntity that = (ChessRoomEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
