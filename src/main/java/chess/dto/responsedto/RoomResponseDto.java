package chess.dto.responsedto;

import java.time.LocalDateTime;

public class RoomResponseDto {
    private final long roomId;
    private final String roomName;
    private final LocalDateTime createdAt;

    public RoomResponseDto(long roomId, String roomName, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.createdAt = createdAt;
    }
}
