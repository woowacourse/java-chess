package chess.command;

import chess.ChessGame;
import chess.view.OutputView;

public final class Status implements CommandStrategy {
    @Override
    public void execute(final String command, final ChessGame chessGame) {
        OutputView.printStatus(chessGame.calculateStatus());
    }
}
