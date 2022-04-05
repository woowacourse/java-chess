package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Color;

public class BlackTurn extends Running {

    protected BlackTurn(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public State move(String source, String target) {
        if (chessBoard.isTurn(source, Color.BLACK)) {
            throw new IllegalArgumentException("black 진영의 차례입니다.");
        }

        chessBoard.move(source, target);

        if (chessBoard.isFinished()) {
            return new BlackWin(chessBoard);
        }

        return new WhiteTurn(chessBoard);
    }

    @Override
    public StateType getStateType() {
        return StateType.BLACK_TURN;
    }
}
