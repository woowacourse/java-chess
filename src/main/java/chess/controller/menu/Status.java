package chess.controller.menu;

import chess.controller.ChessController;

public class Status implements Menu {
    @Override
    public void play(ChessController controller) {
        controller.createStatus();
    }
}
