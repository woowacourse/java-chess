package chess.domain.game;

import chess.domain.CommandAsString;
import chess.domain.game.state.GameState;
import chess.domain.result.Result;

public final class Game {

    private final GameState gameState;

    public Game(final GameState gameState) {
        this.gameState = gameState;
    }

    public Game execute(final CommandAsString command) {
        final GameState newGameState = gameState.execute(command);
        return new Game(newGameState);
    }

    public Result result(final CommandAsString command) {
        if (command.isStatus()) {
            return gameState.statusResult();
        }

        if (command.isShow()) {
            return gameState.pathResult(command.source());
        }
        return gameState.turnResult();
    }

    public boolean isNotFinished() {
        return !gameState.isFinished();
    }
}
