package chess.domain.game.command;

import chess.domain.game.state.GameState;

public interface Command {

    GameState execute();
}
