package chess.domain.command;

import chess.ChessGame;

public final class End implements CommandGenerator {
    @Override
    public void execute(final String command,
                        final ChessGame chessGame,
                        final Runnable printBoardInfoToState) {

        if (!chessGame.isRunning()) {
            chessGame.gameSwitchOff();
            printBoardInfoToState.run();
            return;
        }

        chessGame.end();

        printBoardInfoToState.run();
    }
}
