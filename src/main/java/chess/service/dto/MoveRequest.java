package chess.service.dto;

import chess.entity.Movement;

public class MoveRequest {
    private Long id;
    private String sourceKey;
    private String targetKey;

    public MoveRequest(Long id, String sourceKey, String targetKey) {
        this.id = id;
        this.sourceKey = sourceKey;
        this.targetKey = targetKey;
    }

    public Movement toEntity() {
        return new Movement(id, sourceKey, targetKey);
    }

    public Long getId() {
        return id;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public String getTargetKey() {
        return targetKey;
    }

}
