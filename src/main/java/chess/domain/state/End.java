package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Command;

public class End implements State {

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException("이미 게임이 종료되었습니다.");
    }

    @Override
    public State stop() {
        throw new IllegalArgumentException("이미 게임이 종료되었습니다.");
    }

    @Override
    public State changeTurn(Command command, ChessBoard chessBoard) {
        throw new IllegalArgumentException("게임을 종료합니다.");
    }
}
