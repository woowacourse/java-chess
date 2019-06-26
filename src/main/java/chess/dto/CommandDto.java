package chess.dto;

public class CommandDto {
    private long turn;
    private long roomId;
    private String origin;
    private String target;

    public void setTarget(final String target) {
        this.target = target;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public void setRoomId(final long room_id) {
        this.roomId = room_id;
    }

    public void setTurn(final long turn) {
        this.turn = turn;
    }

    public long getTurn() {
        return turn;
    }

    public long getRoomId() {
        return roomId;
    }

    public String getOrigin() {
        return origin;
    }

    public String getTarget() {
        return target;
    }
}
