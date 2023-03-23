package chess.controller.command;

import chess.controller.ChessController;

public class StatusCommand implements Command {
    @Override
    public void execute(final ChessController chessController) {
        chessController.calculate();
    }
}
