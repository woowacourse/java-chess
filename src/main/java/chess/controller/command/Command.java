package chess.controller.command;

import chess.controller.ChessController;

public interface Command {
    void execute(final ChessController chessController);
}
