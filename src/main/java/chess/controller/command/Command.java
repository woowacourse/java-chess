package chess.controller.command;

import chess.domain.gamestate.GameState;

public interface Command {
    GameState execute(GameState gameState);
}
