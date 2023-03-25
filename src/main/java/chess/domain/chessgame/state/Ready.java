package chess.domain.chessgame.state;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;

public class Ready implements GameState {
    private static final String READY_STATE_EXCEPTION_MESSAGE = "게임을 시작 전에 실행할 수 없는 동작입니다.";

    @Override
    public GameState start() {
        return new Running(new ChessBoard());
    }

    @Override
    public void move(SquareCoordinate from, SquareCoordinate to) {
        throw new IllegalStateException(READY_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isKingDead() {
        throw new IllegalStateException(READY_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public GameState close() {
        throw new IllegalStateException(READY_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public GameState end() {
        return new End();
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public ChessBoard getChessBoard() {
        throw new IllegalStateException(READY_STATE_EXCEPTION_MESSAGE);
    }
}
