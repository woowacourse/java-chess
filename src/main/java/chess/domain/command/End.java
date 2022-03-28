package chess.domain.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class End implements Command {
    private final ChessGame chessGame;

    public End(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execute(final String rawInputCommand, final OutputView outputView) {
        if (chessGame.isNotRunning()) {
            this.chessGame.isEnd = !this.chessGame.isEnd;
            return;
        }
        chessGame.end();
        
        outputView.printFinishMessage();
        outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
        outputView.printResultMessage(chessGame.getResultMessage());
    }
}
