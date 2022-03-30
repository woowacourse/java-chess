package chess.domain.command;

import chess.domain.ChessGame;

public class Status extends Command {
    protected Status() {
        super(Type.STATUS);
    }

    @Override
    public void execute(ChessGame chessGame) {
    }
}
