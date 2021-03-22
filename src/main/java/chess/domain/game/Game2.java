package chess.domain.game;

import chess.domain.CommandAsString;
import chess.domain.game.state.GameState;
import chess.domain.game.state.InitialState;
import chess.domain.board.Board2;

public final class Game2 {

    private final GameState gameState;

    public Game2() {
        this(new InitialState(Board2.initiate()));
    }

    public Game2(final GameState gameState) {
        this.gameState = gameState;
    }

    public Game2 execute(final CommandAsString command) {
        final GameState newGameState = gameState.execute(command);
        return new Game2(newGameState);
    }

    public GameVisual gameVisual(CommandAsString command) {
        if (command.isStatus()) {
            return gameState.gameVisual();
        }
        return gameState.statusVisual();
    }

    public boolean isNotFinished() {
        return !gameState.isFinished();
    }
}
