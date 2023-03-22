package chess.controller.request;

import chess.controller.ChessController;

public abstract class EndRequestType implements RequestType {

    @Override
    public final void execute(ChessController chessController) {
        chessController.finish();
    }
}
