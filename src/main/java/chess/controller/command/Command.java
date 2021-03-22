package chess.controller.command;

import chess.domain.manager.ChessGameManager;

public interface Command {
    ChessGameManager execute(ChessGameManager runningGameManager);
}
