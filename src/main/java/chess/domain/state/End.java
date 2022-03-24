package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Command;

public class End implements State {
    @Override
    public State start() {
        throw new IllegalArgumentException();
    }

    @Override
    public State stop() {
        throw new IllegalArgumentException();
    }

    @Override
    public State changeTurn(Command command, ChessBoard chessBoard) {
        throw new IllegalArgumentException("게임을 종료합니다.");
    }
}
