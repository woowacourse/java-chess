package chess.domain.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public final class End implements CommandStrategy {
    @Override
    public void execute(final String command, final ChessGame chessGame) {
        OutputView.printFinishMessage();
        if (chessGame.isNotRunning()) {
            chessGame.turnOff();
            return;
        }
        chessGame.end();
        
        OutputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
        OutputView.printResultMessage(chessGame.getResultMessage());
    }
}
