package chess.dto;

public class GameRoomDto {
    private final String roomName;
    private final boolean isWhiteTurn;

    public GameRoomDto(String roomName, boolean isWhiteTurn) {
        this.roomName = roomName;
        this.isWhiteTurn = isWhiteTurn;
    }

    public String getRoomName() {
        return roomName;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }
}
