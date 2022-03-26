package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Color;

public class WhiteTurn implements State {

    private final ChessBoard chessBoard;

    protected WhiteTurn(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
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
        if (!chessBoard.isTurn(source, Color.WHITE)) {
            throw new IllegalArgumentException("black 진영의 차례가 아닙니다.");
        }

        chessBoard.move(source, target);

        if (chessBoard.isFinished()) {
            return new WhiteWin(chessBoard);
        }

        // TODO BlackTurn
        return null;
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
