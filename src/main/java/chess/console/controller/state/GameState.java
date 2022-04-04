package chess.console.controller.state;

import chess.console.controller.GameCommand;
import chess.console.service.ChessService;

public interface GameState {
    GameState changeStateBy(GameCommand gameCommand);

    default boolean isRunning(ChessService service) {
        return isNotEnd() && service.isWaitingOrRunning();
    }

    private boolean isNotEnd() {
        return this.getClass().equals(Playing.class)
                || this.getClass().equals(Ready.class);
    }
}
