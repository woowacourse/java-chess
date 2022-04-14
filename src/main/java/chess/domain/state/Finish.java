package chess.domain.state;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessBoard;
import chess.domain.Team;

public class Finish implements GameState {
    private static final String FINISH_INVALID_OPERATION_EXCEPTION = "[ERROR] 게임 종료 상태에서는 MOVE 명령을 실행할 수 없습니다.";

    private final ChessBoard chessBoard;

    public Finish(ChessBoard chessBoard) {
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
        throw new UnsupportedOperationException(FINISH_INVALID_OPERATION_EXCEPTION);
    }

    @Override
    public GameState status() {
        return this;
    }

    @Override
    public Team findWinner() {
        return chessBoard.judgeWinner();
    }

    @Override
    public boolean isPlay() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return true;
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
