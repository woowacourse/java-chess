package chess.domain.game;

import chess.domain.CommandAsString;
import chess.domain.game.state.GameState;

public final class GameManager {

    private final GameState gameState;
    private final CommandAsString command;

    public GameManager(final CommandAsString command, final GameState gameState) {
        this.command = command;
        this.gameState = gameState;
    }

    public GameManager execute(final CommandAsString command) {
        final GameState newGameState = gameState.execute(command);
        return new GameManager(command, newGameState);
    }

    public GameVisual gameVisual() {
        if (command.isStatus()) {
            return gameState.statusVisual();
        }
        return gameState.gameVisual();
    }

    public boolean isNotFinished() {
        return !gameState.isFinished();
    }
}
