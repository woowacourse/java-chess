package chess.db.entity;

import chess.domain.game.GameState;
import java.util.Objects;

public class GameEntity {

    private final int id;
    private final GameState state;

    public GameEntity(int id, GameState state) {
        this.id = id;
        this.state = state;
    }

    public static GameEntity of(int id, String state) {
        return new GameEntity(id, GameState.valueOf(state));
    }

    public int getId() {
        return id;
    }

    public GameState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameEntity that = (GameEntity) o;
        return id == that.id
                && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state);
    }

    @Override
    public String toString() {
        return "GameEntity{" + "id=" + id + ", state=" + state + '}';
    }
}
