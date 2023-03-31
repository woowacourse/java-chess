package chess.dto;

import chess.view.GameState;

public class LoadCommand {
    private final GameState gameState;
    private final long gameId;

    private LoadCommand(final GameState gameState, final long gameId) {
        this.gameState = gameState;
        this.gameId = gameId;
    }

    public static LoadCommand create(final GameState gameState, final long gameId) {
        return new LoadCommand(gameState, gameId);
    }

    public GameState getGameState() {
        return gameState;
    }

    public long getGameId() {
        return gameId;
    }
}
