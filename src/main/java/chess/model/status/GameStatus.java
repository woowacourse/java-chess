package chess.model.status;

import chess.model.GameCommand;

public interface GameStatus {
    GameStatus changeStatus(GameCommand gameCommand);

    default boolean isRunning() {
        return this.getClass().equals(Playing.class) || this.getClass().equals(Ready.class);
    }
}
