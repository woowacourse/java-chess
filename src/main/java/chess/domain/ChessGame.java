package chess.domain;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;

public final class ChessGame {

    private ChessBoard chessBoard;
    private GameState state = GameState.READY;

    public void start() {
        if (state.isRunning()) {
            throw new IllegalArgumentException("게임을 진행 중에는 새로운 게임을 시작할 수 없습니다.");
        }

        state = GameState.WHITE;
        chessBoard = new ChessBoard();
    }

    public void move(final SquareCoordinate from, final SquareCoordinate to) {
        if (!state.isRunning()) {
            throw new IllegalArgumentException("게임을 시작전에는 말을 움직일 수 없습니다.");
        }

        if (!chessBoard.isRightTeam(state.getCurrentTeam(), from)) {
            throw new IllegalArgumentException("잘못된 팀의 말을 선택하였습니다.");
        }

        chessBoard.move(from, to);
        state = state.convertTurn();
    }

    public void end() {
        state = GameState.END;
    }

    public boolean isNotEnd() {
        return state != GameState.END;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
