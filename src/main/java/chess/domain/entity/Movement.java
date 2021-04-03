package chess.domain.entity;

import java.util.UUID;

public class Movement {
    private String id;
    private String chessId;
    private String sourcePosition;
    private String targetPosition;

    public Movement(final String chessId, final String sourcePosition, final String targetPosition) {
        this(UUID.randomUUID().toString(), chessId, sourcePosition, targetPosition);
    }

    public Movement(final String id, final String chessId, final String sourcePosition, final String targetPosition) {
        this.id = id;
        this.chessId = chessId;
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public String getId() {
        return id;
    }

    public String getChessId() {
        return chessId;
    }

    public String getSourcePosition() {
        return sourcePosition;
    }

    public String getTargetPosition() {
        return targetPosition;
    }
}
