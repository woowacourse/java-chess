package chess.controller.menu;

import chess.controller.ChessController;
import chess.view.OutputView;

public class End implements Menu {

    @Override
    public void play(ChessController chessController) {
        chessController.checkReady();
        try {
            chessController.end();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }
}
