package chessgame.dto;

public class GameRoomDto {

    private final long roomId;
    private final String turn;

    public GameRoomDto(long roomId, String turn) {
        this.roomId = roomId;
        this.turn = turn;
    }

    public long getRoomId() {
        return roomId;
    }

    public String getTurn() {
        return turn;
    }
}
