package chess.entity;

import java.util.Objects;

public final class ChessGameEntity {
    private final Long id;
    private final String currentCamp;
    private final Long userId;

    public ChessGameEntity(final Long id, final String currentCamp, final Long userId) {
        this.id = id;
        this.currentCamp = currentCamp;
        this.userId = userId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ChessGameEntity that = (ChessGameEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(currentCamp, that.currentCamp) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currentCamp, userId);
    }
}
