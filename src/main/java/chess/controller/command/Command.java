package chess.controller.command;

import chess.service.ChessService;

public interface Command {
    void execute(final ChessService chessService);
}
