package chess.domain.command;

import chess.ChessGame;

public final class Start implements CommandStrategy {
    @Override
    public void execute(final String command,
                        final ChessGame chessGame,
                        final Runnable runnable) {
        chessGame.start();
        
        runnable.run();
    }
}
