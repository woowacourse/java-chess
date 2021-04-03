package chess.domain.entity;

import java.util.UUID;

public class Movement {
    private String id;
    private String chessId;
    private String sourcePosition;
    private String targetPosition;

    public Movement(final String chessId, final String sourcePosition, final String targetPosition) {
        this.id = UUID.randomUUID().toString();
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
