package chess.dto.response;

import chess.domain.game.GameState;

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
}