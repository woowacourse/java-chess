package chess.controller.command;

import chess.domain.game.ChessGame;

public final class EndCommand implements Command {
    @Override
    public void execute(final ChessGame chessGame) {
        chessGame.end();
    }
}
