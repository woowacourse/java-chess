package chess.controller.state;

import chess.controller.GameCommand;
import chess.service.ChessService;

public interface GameState {
    GameState execute(GameCommand gameCommand);

    default boolean isRunning(ChessService service) {
        return isNotEnd() && service.isBoardReadyOrRunning();
    }

    private boolean isNotEnd() {
        return this.getClass().equals(Playing.class)
                || this.getClass().equals(Ready.class);
    }
}
