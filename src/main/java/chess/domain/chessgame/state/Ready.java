package chess.domain.chessgame.state;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;
import chess.domain.piece.Team;
import chess.domain.winningstatus.WinningStatus;
import chess.domain.winningstatus.WinningStatusByKing;

public class Ready implements GameState {
    private static final String READY_STATE_EXCEPTION_MESSAGE = "게임을 시작 전에 실행할 수 없는 동작입니다.";

    private final WinningStatusByKing winningStatusByKing;

    public Ready() {
        this.winningStatusByKing = null;
    }

    public Ready(WinningStatusByKing winningStatusByKing) {
        this.winningStatusByKing = winningStatusByKing;
    }

    @Override
    public GameState start() {
        return new Running(new ChessBoard(), Team.WHITE);
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

    public WinningStatus status() {
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
