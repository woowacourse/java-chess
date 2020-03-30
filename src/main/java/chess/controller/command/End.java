package chess.controller.command;

import chess.domain.gamestate.GameState;

public class End implements Command {
    @Override
    public GameState execute(GameState gameState) {
        return gameState.finish();
    }
}
