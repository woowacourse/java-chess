package chess.controller;

import chess.command.Command;
import chess.domain.game.Game;
import chess.history.History;

@FunctionalInterface
public interface Executor {
    
    Game execute(Command command, Game game, History history);
}
