package chess.controller.request;

import chess.controller.ChessController;

public class StartRequestType implements RequestType {

    @Override
    public final void execute(ChessController chessController) {
        chessController.start();
    }
}
