package chess.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Movement {
    private String id;
    private String chessId;
    private String sourcePosition;
    private String targetPosition;
    private LocalDateTime createdDate;


    public Movement(final String chessId, final String sourcePosition, final String targetPosition) {
        this(UUID.randomUUID().toString(), chessId, sourcePosition, targetPosition, LocalDateTime.now());
    }

    public Movement(final String id, final String chessId, final String sourcePosition, final String targetPosition, final LocalDateTime createdDate) {
        this.id = id;
        this.chessId = chessId;
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
        this.createdDate = createdDate;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return Objects.equals(id, movement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chessId, sourcePosition, targetPosition);
    }
}
