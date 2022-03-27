package console.view;

import chess.ChessBoard;

public class StatusCommand implements Command {

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public ChessBoard execute(ChessBoard chessBoard) {
        return null;
    }
}
