package chess.command;

import chess.ChessGame;

public final class End implements CommandStrategy {
    @Override
    public void execute(final String command, final ChessGame chessGame) {
        if (chessGame.isNotRunning()) {
            chessGame.turnOff();
            return;
        }
        chessGame.end();
//        OutputView.printStatus(chessGame.calculateStatus());
    }
}
