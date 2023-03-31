package chess.domain.chessgame.state;

import chess.dao.ChessGameData;
import chess.dao.RoomName;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;
import chess.domain.winningstatus.WinningStatus;

public class End implements GameState {
    private static final String END_STATE_EXCEPTION_MESSAGE = "종료 상태일때 수행할 수 없는 동작입니다.";

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public GameState start() {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public void move(SquareCoordinate from, SquareCoordinate to) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isKingDead() {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public GameState close() {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public WinningStatus status() {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public GameState save(RoomName roomName) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public GameState load(ChessGameData chessGameData) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public GameState end() {
        return new End();
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }

    @Override
    public ChessBoard getChessBoard() {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }
}
