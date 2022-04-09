package chess.dto;

import chess.domain.Team;

public class RoomDto {
    private final Long id;
    private final Team status;

    public RoomDto(Long id, Team status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Team getStatus() {
        return status;
    }
}
