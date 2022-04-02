package chess.domain.command;

import chess.ChessGame;

public final class Status implements CommandGenerator {
    @Override
    public void execute(final String command,
                        final ChessGame chessGame,
                        final Runnable printBoardInfoToState) {
        chessGame.status();

        printBoardInfoToState.run();

        chessGame.run();
    }
}
