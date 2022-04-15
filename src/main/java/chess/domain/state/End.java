package chess.domain.state;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessBoard;
import chess.domain.Team;

public class End implements GameState {

    private static final String END_INVALID_OPERATION_EXCEPTION = "[ERROR] 프로그램 종료 상태에서는 명령을 실행할 수 없습니다.";

    @Override
    public GameState start() {
        throw new UnsupportedOperationException(END_INVALID_OPERATION_EXCEPTION);
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException(END_INVALID_OPERATION_EXCEPTION);
    }

    @Override
    public GameState move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        throw new UnsupportedOperationException(END_INVALID_OPERATION_EXCEPTION);
    }

    @Override
    public GameState status() {
        throw new UnsupportedOperationException(END_INVALID_OPERATION_EXCEPTION);
    }

    @Override
    public Team findWinner() {
        throw new UnsupportedOperationException(END_INVALID_OPERATION_EXCEPTION);
    }

    @Override
    public boolean isPlay() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public ChessBoard getChessBoard() {
        throw new UnsupportedOperationException(END_INVALID_OPERATION_EXCEPTION);
    }
}
