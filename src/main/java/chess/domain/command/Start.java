package chess.domain.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public final class Start implements CommandStrategy {
    @Override
    public void execute(final String command, final ChessGame chessGame) {
        chessGame.start();

        OutputView.printBoard(chessGame.getBoard());
    }
}
