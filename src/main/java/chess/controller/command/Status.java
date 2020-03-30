package chess.controller.command;

import chess.domain.gamestate.GameState;

public class Status implements Command {
    @Override
    public GameState execute(GameState gameState) {
        return gameState.finish();
    }
}
