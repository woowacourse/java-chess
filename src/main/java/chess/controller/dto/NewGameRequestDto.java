package chess.controller.dto;

public class NewGameRequestDto {

    private final String whiteUsername;
    private final String blackUsername;
    private final String roomName;

    public NewGameRequestDto(String whiteUsername, String blackUsername, String roomName) {
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.roomName = roomName;
    }

    public String getWhiteUsername() {
        return whiteUsername;
    }

    public String getBlackUsername() {
        return blackUsername;
    }

    public String getRoomName() {
        return roomName;
    }
}
