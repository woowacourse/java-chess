package chess.controller.menu;

import chess.controller.ChessController;
import chess.domain.position.Position;
import chess.view.OutputView;

public class Move implements Menu {

    private final Position from;
    private final Position to;

    public Move(String from, String to) {
        this.from = new Position(from);
        this.to = new Position(to);
    }

    @Override
    public void play(ChessController chessController) {
        chessController.checkReady();
        try {
            chessController.move(from, to);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }
}
