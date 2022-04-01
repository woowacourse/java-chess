package chess.domain.command;

import chess.ChessGame;

public final class End implements CommandGenerator {
    @Override
    public void execute(final String command,
                        final ChessGame chessGame,
                        final Runnable printBoardToState) {

        if (chessGame.isNotRunning()) {
            chessGame.gameSwitchOff();
            printBoardToState.run();
            return;
        }

        chessGame.end();
        printBoardToState.run();
    }
}
