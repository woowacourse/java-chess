package chess.controller;

import chess.command.Command2;
import chess.domain.game.Game2;
import chess.history.History2;

@FunctionalInterface
public interface Executor2 {
    
    Game2 execute(Command2 command, Game2 game, History2 history);
}
