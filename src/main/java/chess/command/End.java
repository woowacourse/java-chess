package chess.command;

import chess.domain.ChessGame;

public class End extends Command {

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
