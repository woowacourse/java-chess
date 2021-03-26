package chess.domain.game;

import chess.domain.CommandAsString;
import chess.domain.game.state.GameState;

public final class GameManager {

    private final GameState gameState;

    public GameManager(final GameState gameState) {
        this.gameState = gameState;
    }

    public GameManager execute(final CommandAsString command) {
        final GameState newGameState = gameState.execute(command);
        return new GameManager(newGameState);
    }

    public GameVisual gameVisual(final CommandAsString command) {
        if (command.isStatus()) {
            return gameState.statusVisual();
        }
        return gameState.gameVisual();
    }

    public boolean isNotFinished() {
        return !gameState.isFinished();
    }
}
