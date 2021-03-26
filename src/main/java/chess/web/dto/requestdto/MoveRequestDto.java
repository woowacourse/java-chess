package chess.web.dto.requestdto;

public class MoveRequestDto {
    private final String sourcePosition;
    private final String targetPosition;

    public MoveRequestDto(String sourcePosition, String targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public String getTargetPosition() {
        return targetPosition;
    }

    public String getSourcePosition() {
        return sourcePosition;
    }
}
