package chess.dto.requestdto;

public class StartRequestDto {
    private final String roomName;

    public StartRequestDto(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }
}
