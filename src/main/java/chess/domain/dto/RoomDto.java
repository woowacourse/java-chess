package chess.domain.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RoomDto {

    private final long id;
    private final long userId;
    private final String name;
    private final String state;
    private final String currentTeam;
    private final Timestamp createdAt;

    public RoomDto(long id, long userId, String name, String state, String currentTeam,
        Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.state = state;
        this.currentTeam = currentTeam;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getCurrentTeam() {
        return currentTeam;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
