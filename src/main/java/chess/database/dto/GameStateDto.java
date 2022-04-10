package chess.database.dto;

import chess.domain.game.GameState;

public class GameStateDto {

    private final String state;
    private final String turnColor;

    private GameStateDto(String state, String turnColor) {
        this.state = state;
        this.turnColor = turnColor;
    }

    public static GameStateDto of(GameState state) {
        return new GameStateDto(
            state.getState(),
            state.getColor()
        );
    }

    public String getState() {
        return state;
    }

    public String getTurnColor() {
        return turnColor;
    }
}
