package chess.dto;

public class ChessPositionDTO {
    private String sourcePosition;
    private String targetPosition;

    public ChessPositionDTO(String sourcePosition, String targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public String getSourcePosition() {
        return sourcePosition;
    }

    public String getTargetPosition() {
        return targetPosition;
    }

}
