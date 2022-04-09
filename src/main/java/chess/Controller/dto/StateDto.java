package chess.Controller.dto;

import chess.domain.GameState;
import java.util.Locale;

public class StateDto {

    private final String state;

    private StateDto(final GameState gameState) {
        final String state = gameState.toString();
        this.state = state.toUpperCase(Locale.ROOT);
    }

    public static StateDto fromEntity(final GameState gameState) {
        return new StateDto(gameState);
    }

    public String getState() {
        return state;
    }
}
