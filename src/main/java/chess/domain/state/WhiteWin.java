package chess.domain.state;

import chess.domain.ChessBoard;

public class WhiteWin extends Started {

    protected WhiteWin(ChessBoard chessBoard) {
        super(chessBoard);
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
}
