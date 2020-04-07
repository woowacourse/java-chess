package chess.entity;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    private Long id;
    private LocalDateTime createdTime;

    public BaseEntity() {
    }

    public BaseEntity(Long id, LocalDateTime createdTime) {
        this.id = id;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

}
