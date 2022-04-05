package chess.domain.command;

import chess.console.ChessGame;

public final class Start implements CommandGenerator {
    @Override
    public void execute(final String command,
                        final ChessGame chessGame,
                        final Runnable printBoardInfoToState) {
        chessGame.run();

        printBoardInfoToState.run();
    }
}
