package chess.command;

import chess.ChessGame;

public final class Start implements CommandStrategy {
    @Override
    public void execute(final String command, final ChessGame chessGame) {
        chessGame.start();
//        OutputView.printBoard(chessGame.getBoard());
    }
}
