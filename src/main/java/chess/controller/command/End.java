package chess.controller.command;

import chess.domain.ChessGame;

public class End implements Command {

    private static final End INSTANCE = new End();

    private End() {
    }

    public static End getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(final ChessGame chessGame) {
        chessGame.end();
    }
}
