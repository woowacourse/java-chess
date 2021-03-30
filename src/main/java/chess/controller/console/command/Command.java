package chess.controller.console.command;

import chess.domain.manager.ChessGameManager;

@FunctionalInterface
public interface Command {
    ChessGameManager execute(ChessGameManager runningGameManager);
}
