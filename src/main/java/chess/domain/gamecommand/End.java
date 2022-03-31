package chess.domain.gamecommand;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class End implements Command {

    private final ChessGame chessGame;

    public End(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execute(final String rawInputCommand, final OutputView outputView) {
        outputView.printFinishMessage();
        if (chessGame.isNotRunning()) {
            this.chessGame.off();
            return;
        }
        chessGame.end();
        outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
        outputView.printResultMessage(chessGame.getResultMessage());
    }
}

