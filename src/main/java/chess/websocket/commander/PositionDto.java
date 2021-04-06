package chess.websocket.commander;

public class PositionDto {
    private String currentPosition;
    private String targetPosition;

    public PositionDto(String currentPosition, String targetPosition) {
        this.currentPosition = currentPosition;
        this.targetPosition = targetPosition;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(String targetPosition) {
        this.targetPosition = targetPosition;
    }
}
