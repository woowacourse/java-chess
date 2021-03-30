package chess.dto;

public class PositionDTO {
    private String source;
    private String target;

    public PositionDTO(String sourcePosition, String targetPosition) {
        this.source = sourcePosition;
        this.target = targetPosition;
    }

    public String getSourcePosition() {
        return source;
    }

    public String getTargetPosition() {
        return target;
    }
}
