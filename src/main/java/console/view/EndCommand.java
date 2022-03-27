package console.view;

import chess.ChessBoard;

public class EndCommand implements Command {

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public ChessBoard execute(ChessBoard chessBoard) {
        throw new IllegalStateException();
    }
}
