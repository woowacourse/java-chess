package chess.controller;

import chess.command.Command;
import chess.domain.game.ChessGame;

@FunctionalInterface
public interface Executor {
    
    void execute(Command command, ChessGame action);
}
