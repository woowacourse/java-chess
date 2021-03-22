package chess.controller.console.command;

import chess.domain.manager.ChessGameManager;

public interface Command {
    ChessGameManager execute(ChessGameManager runningGameManager);
}
