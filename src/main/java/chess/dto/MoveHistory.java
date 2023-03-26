package chess.dto;

public class MoveHistory {

    private final String sourcePosition;
    private final String targetPosition;

    MoveHistory(String sourcePosition, String targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public static MoveHistory of(String sourcePosition, String targetPosition) {
        return new MoveHistory(sourcePosition, targetPosition);
    }

    public String getSource() {
        return sourcePosition;
    }

    public String getTarget() {
        return targetPosition;
    }
}
