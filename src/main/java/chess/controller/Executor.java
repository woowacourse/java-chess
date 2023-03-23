package chess.controller;

import chess.command.Command;
import chess.domain.game.Game;

@FunctionalInterface
public interface Executor {
    
    void execute(Command command, Game game);
}
