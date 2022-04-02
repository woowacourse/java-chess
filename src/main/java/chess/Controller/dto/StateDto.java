package chess.Controller.dto;

import chess.domain.GameState;
import chess.domain.board.Board;
import java.util.Locale;

public class StateDto {

    private final String state;

    private StateDto(final GameState gameState) {
        final String state = gameState.toString();
        this.state = state.toUpperCase(Locale.ROOT);
    }

    public static StateDto fromEntity(final Board board) {
        return new StateDto(board.getGameState());
    }

    public String getState() {
        return state;
    }
}
