package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Color;

public class BlackTurn extends Started {

    protected BlackTurn(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        return new End(chessBoard);
    }

    @Override
    public State move(String source, String target) {
        if (!chessBoard.isTurn(source, Color.BLACK)) {
            throw new IllegalArgumentException("white 진영의 차례가 아닙니다.");
        }

        chessBoard.move(source, target);

        if (chessBoard.isFinished()) {
            return new BlackWin(chessBoard);
        }

        return new WhiteTurn(chessBoard);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public ChessBoard chessBoard() {
        return chessBoard;
    }
}
