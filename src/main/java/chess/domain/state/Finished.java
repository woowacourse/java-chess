package chess.domain.state;

import chess.domain.ChessBoard;

public abstract class Finished extends Started {

    protected Finished(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public State start() {
        chessBoard.init();
        return new WhiteTurn(chessBoard);
    }

    @Override
    public final State end() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_ERROR_MASSAGE);
    }

    @Override
    public final State move(String source, String target) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_ERROR_MASSAGE);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public final boolean isFinished() {
        return true;
    }
}
