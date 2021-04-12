package chess.dto;

public class MovablePositionDto {
    private String roomId;
    private String target;

    public String getRoomId() {
        return roomId;
    }

    public String getTarget() {
        return target;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
