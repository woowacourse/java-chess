package chess.view.command;

import chess.domain.ChessBoard;

public class WhiteRunning extends Running {

    public WhiteRunning(final ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    protected final Running otherCommand(final ChessBoard chessBoard) {
        return new BlackRunning(chessBoard);
    }
}
