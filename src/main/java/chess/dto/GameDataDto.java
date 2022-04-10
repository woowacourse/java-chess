package chess.dto;

import chess.domain.game.statistics.GameState;
import java.util.Objects;

public class GameDataDto {

    private final int id;
    private final GameState state;

    public GameDataDto(int id, GameState state) {
        this.id = id;
        this.state = state;
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
        GameDataDto that = (GameDataDto) o;
        return id == that.id
                && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state);
    }

    @Override
    public String toString() {
        return "GameDataDto{" + "id=" + id + ", state=" + state + '}';
    }
}