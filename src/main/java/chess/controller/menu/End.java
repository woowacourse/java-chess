package chess.controller.menu;

import chess.controller.ChessController;

public class End implements Menu {

    @Override
    public void play(ChessController chessController) {
        chessController.makeEnd();
    }
}
