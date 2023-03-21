package chess.controller.command;

import chess.controller.ChessController;
import chess.domain.ChessGame;

public class EndCommand extends Command {
    public EndCommand(ChessController chessController) {
        super(chessController);
    }

    @Override
    public boolean operate(ChessGame chessGame) {
        return false;
    }
}
