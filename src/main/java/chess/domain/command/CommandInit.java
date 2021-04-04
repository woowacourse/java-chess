package chess.domain.command;

import chess.domain.game.ChessGame;

public abstract class CommandInit implements Command {
    protected final ChessGame chessGame;

    public CommandInit(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
