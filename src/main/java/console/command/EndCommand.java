package console.command;

import chess.ChessBoard;

public class EndCommand implements Command {

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public ChessBoard execute(ChessBoard chessBoard) {
        throw new IllegalStateException();
    }
}
