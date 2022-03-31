package chess.controller.menu;

import chess.controller.ChessController;
import chess.domain.position.Position;
import chess.view.OutputView;

public class Move implements Menu {

    private final Position before;
    private final Position after;

    public Move(String before, String after) {
        this.before = new Position(before);
        this.after = new Position(after);
    }

    @Override
    public void play(ChessController chessController) {
        chessController.checkReady();
        try {
            chessController.move(before, after);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }
}
