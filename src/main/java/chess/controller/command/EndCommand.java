package chess.controller.command;

import chess.controller.ChessController;

public class EndCommand implements Command {
    @Override
    public void execute(final ChessController chessController) {
        chessController.end();
    }
}
