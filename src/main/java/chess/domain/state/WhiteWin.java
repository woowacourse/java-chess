package chess.domain.state;

import chess.domain.ChessBoard;

public class WhiteWin implements State {

    private final ChessBoard chessBoard;

    protected WhiteWin(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(String source, String target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public ChessBoard chessBoard() {
        return chessBoard;
    }
}
