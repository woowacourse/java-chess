package chess.domain.chessgame.state;

import chess.dao.ChessGameData;
import chess.dao.RoomName;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessFactory;
import chess.domain.chessboard.SquareCoordinate;
import chess.domain.piece.Team;
import chess.domain.winningstatus.WinningStatus;
import chess.domain.winningstatus.WinningStatusByKing;

public class Ready implements GameState {
    private static final String READY_STATE_EXCEPTION_MESSAGE = "게임을 시작 전에 실행할 수 없는 동작입니다.";

    private final WinningStatusByKing winningStatusByKing;
    private final ChessBoard chessBoard;

    public Ready() {
        this.winningStatusByKing = null;
        this.chessBoard = null;
    }

    public Ready(final WinningStatusByKing winningStatusByKing, final ChessBoard chessBoard) {
        this.winningStatusByKing = winningStatusByKing;
        this.chessBoard = chessBoard;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public GameState start() {
        return new Running(new ChessBoard(ChessFactory.create()), Team.WHITE);
    }

    @Override
    public void move(final SquareCoordinate from, final SquareCoordinate to) {
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
        if (winningStatusByKing == null) {
            throw new IllegalStateException(READY_STATE_EXCEPTION_MESSAGE);
        }
        return winningStatusByKing;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public GameState save(RoomName roomName) {
        throw new IllegalStateException(READY_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public GameState load(ChessGameData chessGameData) {
        return new Running(chessGameData.getChessBoard(), chessGameData.getTurn());
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
        if (chessBoard == null) {
            throw new IllegalStateException(READY_STATE_EXCEPTION_MESSAGE);
        }
        return chessBoard;
    }
}
