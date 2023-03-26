package chess.dto;

public class MoveDto {
    private final int id;
    private final String sourcePosition;
    private final String targetPosition;

    MoveDto(int id, String sourcePosition, String targetPosition) {
        this.id = id;
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    MoveDto(String sourcePosition, String targetPosition) {
        this(0, sourcePosition, targetPosition);
    }

    public static MoveDto of(String sourcePosition, String targetPosition) {
        return new MoveDto(sourcePosition, targetPosition);
    }

    public static MoveDto of(int id, String sourcePosition, String targetPosition) {
        return new MoveDto(id, sourcePosition, targetPosition);
    }


    public String getSource() {
        return sourcePosition;
    }

    public String getTarget() {
        return targetPosition;
    }

    public int getId() {
        return id;
    }
}
