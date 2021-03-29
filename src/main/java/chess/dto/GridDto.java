package chess.dto;

import chess.dto.responsedto.ResponseDto;

import java.time.LocalDateTime;

public class GridDto implements ResponseDto {
    private final Long gridId;
    private final boolean isBlackTurn;
    private final boolean isFinished;
    private final Long roomId;
    private final LocalDateTime createdAt;
    private final boolean isStarted;

    public GridDto(Long gridId, boolean isBlackTurn, boolean isFinished, Long roomId, LocalDateTime createdAt, boolean isStarted) {
        this.gridId = gridId;
        this.isBlackTurn = isBlackTurn;
        this.isFinished = isFinished;
        this.roomId = roomId;
        this.createdAt = createdAt;
        this.isStarted = isStarted;
    }

    public Long getGridId() {
        return gridId;
    }

    public boolean isBlackTurn() {
        return isBlackTurn;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Long getRoomId() {
        return roomId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isStarted() {
        return isStarted;
    }
}
