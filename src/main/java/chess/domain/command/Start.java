package chess.domain.command;

import chess.domain.ChessGame;

public class Start extends Command {
    protected Start() {
        super(Type.START);
    }

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.start();
    }
}
