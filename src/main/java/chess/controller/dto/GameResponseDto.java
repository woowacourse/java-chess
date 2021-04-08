package chess.controller.dto;

public class GameResponseDto {

    private final String whiteUsername;
    private final String blackUsername;
    private final String roomName;

    public GameResponseDto(final String whiteUsername, final String blackUsername, final String roomName) {
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
