package chess.controller.console.command;

import chess.domain.manager.ChessGameManager;
import chess.service.ChessService;

@FunctionalInterface
public interface Command {
    ChessGameManager execute(ChessService chessService, long gameId);
}
