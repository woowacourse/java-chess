package chess.model.dto;

public class CreateRoomDto {

    private final String roomName;
    private final String roomPassword;

    public CreateRoomDto(String roomName, String roomPassword) {
        this.roomName = roomName;
        this.roomPassword = roomPassword;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomPassword() {
        return roomPassword;
    }
}
