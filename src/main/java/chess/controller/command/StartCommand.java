package chess.controller.command;

import chess.controller.ChessController;

public class StartCommand implements Command {
    @Override
    public void execute(final ChessController chessController) {
        chessController.start();
    }
}
