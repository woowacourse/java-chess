package chess.domain.state;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessBoard;
import chess.domain.Team;

public class Ready implements GameState {
    private static final String READY_INVALID_OPERATION_EXCEPTION = "[ERROR] 게임 대기 상태에서는 실행할 수 없습니다.";

    private final ChessBoard chessBoard;

    public Ready(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState start() {
        return new WhiteTurn(ChessBoard.create());
    }

    @Override
    public GameState end() {
        return new End();
    }

    @Override
    public GameState move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        throw new UnsupportedOperationException(READY_INVALID_OPERATION_EXCEPTION);
    }

    @Override
    public GameState status() {
        throw new UnsupportedOperationException(READY_INVALID_OPERATION_EXCEPTION);
    }

    @Override
    public Team findWinner() {
        throw new UnsupportedOperationException(READY_INVALID_OPERATION_EXCEPTION);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
