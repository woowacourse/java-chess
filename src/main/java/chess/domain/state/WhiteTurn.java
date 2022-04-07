package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Color;

public class WhiteTurn extends Running {

    protected WhiteTurn(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public State move(String source, String target) {
        if (chessBoard.isTurn(source, Color.BLACK)) {
            throw new IllegalArgumentException("black 진영의 차례가 아닙니다.");
        }

        chessBoard.move(source, target);

        if (chessBoard.isFinished()) {
            return new WhiteWin(chessBoard);
        }

        return new BlackTurn(chessBoard);
    }

    @Override
    public StateType getStateType() {
        return StateType.WHITE_TURN;
    }
}
