package web.dto;

public class MoveInfoDto implements GameDto{

    private final String roomName;
    private final String from;
    private final String to;

    public MoveInfoDto(String roomName, String from, String to) {
        this.roomName = roomName;
        this.from = from;
        this.to = to;
    }

    @Override
    public String getRoomName() {
        return roomName;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
