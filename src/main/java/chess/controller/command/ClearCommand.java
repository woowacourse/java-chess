package chess.controller.command;

import chess.controller.ChessController;

public class ClearCommand implements Command {
    @Override
    public void execute(ChessController chessController) {
        chessController.clear();
    }
}
