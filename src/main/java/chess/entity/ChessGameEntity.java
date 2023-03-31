package chess.entity;

public final class ChessGameEntity {
    private Long id;
    private final String currentCamp;
    private final Long userId;

    public ChessGameEntity(final Long id, final String currentCamp, final Long userId) {
        this.id = id;
        this.currentCamp = currentCamp;
        this.userId = userId;
    }

    public ChessGameEntity(final String currentCamp, final Long userId) {
        this.currentCamp = currentCamp;
        this.userId = userId;
    }
    
    public Long getId() {
        return id;
    }

    public String getCurrentCamp() {
        return currentCamp;
    }

    public Long getUserId() {
        return userId;
    }
}
