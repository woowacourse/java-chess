package chess.model.dto;

public class DeleteRoomDto {

    private final int roomId;

    public DeleteRoomDto(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }
}
