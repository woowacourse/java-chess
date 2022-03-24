package chess.view.command;

import chess.domain.ChessBoard;

public class BlackRunning extends Running {

    public BlackRunning(final ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    protected final Running otherCommand(final ChessBoard chessBoard) {
        return new WhiteRunning(chessBoard);
    }
}
