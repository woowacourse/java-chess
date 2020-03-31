package chess.controller.command;

import chess.domain.gamestate.GameState;
import chess.domain.gamestate.NothingHappened;

public class None implements Command {
    @Override
    public GameState execute(GameState gameState) {
        return new NothingHappened();
    }
}
