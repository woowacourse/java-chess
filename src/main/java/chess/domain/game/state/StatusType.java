package chess.domain.game.state;

public enum StatusType {
    END("end"),
    PLAYING("playing"),
    START("start");

    private final String statusName;

    StatusType(String start) {
        statusName = start;
    }

    public String getStatusName() {
        return statusName;
    }
}
