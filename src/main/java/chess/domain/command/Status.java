package chess.domain.command;

import chess.ChessGame;

public final class Status implements CommandStrategy {
    @Override
    public void execute(final String command, final ChessGame chessGame) {
        chessGame.status();
    }
}
