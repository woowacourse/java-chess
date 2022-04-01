package chess.command;

import chess.domain.ChessGame;

public class End implements Command {

    @Override
    public void execute(final ChessGame chessGame) {
        chessGame.end();
    }
}
