package chess.domain.command;

import chess.ChessGame;

public final class Start implements CommandGenerator {
    @Override
    public void execute(final String command,
                        final ChessGame chessGame,
                        final Runnable printBoardToState) {
        chessGame.run();

        printBoardToState.run();
    }
}
