package chess.controller.command;

import chess.domain.gamestate.GameState;
import chess.domain.gamestate.Running;

public class Start implements Command {
    @Override
    public GameState execute(GameState gameState) {
        return new Running();
    }
}
