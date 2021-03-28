package chess.dto.responsedto;

import java.time.LocalDateTime;

public class GridResponseDto implements ResponseDto {
    private final Long gridID;
    private final boolean isBlackTurn;
    private final boolean isFinished;
    private final Long roomId;
    private final LocalDateTime createdAt;

    public GridResponseDto(Long gridID, boolean isBlackTurn, boolean isFinished, Long roomId, LocalDateTime createdAt) {
        this.gridID = gridID;
        this.isBlackTurn = isBlackTurn;
        this.isFinished = isFinished;
        this.roomId = roomId;
        this.createdAt = createdAt;
    }

    public Long getGridID() {
        return gridID;
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
