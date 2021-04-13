package chess.controller.web.dto.game;

public class GameResponseDto {

    private final Long id;
    private final String whiteUsername;
    private final String blackUsername;
    private final String roomName;

    public GameResponseDto(Long id, String whiteUsername, String blackUsername, String roomName) {
        this.id = id;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.roomName = roomName;
    }

    public Long getId() {
        return id;
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
