package chess.controller.command;

import chess.domain.ChessGameManager;

public interface Command {
    void execute(ChessGameManager chessGameManager);
}
