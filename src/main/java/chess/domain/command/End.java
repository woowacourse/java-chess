package chess.domain.command;

import chess.domain.ChessGame;

public class End extends Command {
    protected End() {
        super(Type.END);
    }

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.end();
    }
}
