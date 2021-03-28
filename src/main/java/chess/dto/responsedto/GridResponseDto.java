package chess.dto.responsedto;

import java.time.LocalDateTime;

public class GridResponseDto implements ResponseDto {
    private final Long gridId;
    private final boolean isBlackTurn;
    private final boolean isFinished;
    private final Long roomId;
    private final LocalDateTime createdAt;

    public GridResponseDto(Long gridId, boolean isBlackTurn, boolean isFinished, Long roomId, LocalDateTime createdAt) {
        this.gridId = gridId;
        this.isBlackTurn = isBlackTurn;
        this.isFinished = isFinished;
        this.roomId = roomId;
        this.createdAt = createdAt;
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
}
