package chess.domain.gamecommand;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class End implements GameCommand {

    private final ChessGame chessGame;

    public End(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execute(final String rawInputCommand) {
        OutputView.printFinishMessage();
        if (chessGame.isNotRunning()) {
            this.chessGame.off();
            return;
        }
        chessGame.end();
        OutputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
        OutputView.printResultMessage(chessGame.getResultMessage());
    }
}

