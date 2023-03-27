package chess.dto;

import java.time.LocalDateTime;

public class MoveDto {
    private final int id;
    private final String sourcePosition;
    private final String targetPosition;
    private final LocalDateTime moveTime;

    MoveDto(int id, String sourcePosition, String targetPosition, LocalDateTime moveTime) {
        this.id = id;
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
        this.moveTime = moveTime;
    }

    MoveDto(String sourcePosition, String targetPosition) {
        this(0, sourcePosition, targetPosition, LocalDateTime.now());
    }

    public static MoveDto of(String sourcePosition, String targetPosition) {
        return new MoveDto(sourcePosition, targetPosition);
    }

    public static MoveDto of(int id, String sourcePosition, String targetPosition, LocalDateTime moveTime) {
        return new MoveDto(id, sourcePosition, targetPosition, moveTime);
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

    public LocalDateTime getMoveTime() {
        return moveTime;
    }
}
