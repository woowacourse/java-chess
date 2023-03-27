package chess.domain.entity;

import java.util.Objects;

public class GameEntity {
    private final int gameId;
    private final String turn;

    public GameEntity(int gameId, String turn) {
        this.gameId = gameId;
        this.turn = turn;
    }

    public int getGameId() {
        return gameId;
    }

    public String getTurn() {
        return turn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameEntity that = (GameEntity) o;
        return gameId == that.gameId && Objects.equals(turn, that.turn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, turn);
    }
}
