package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.GameResult;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Positions;

public class Ready implements State {

    private static final String CANT_MOVE_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.";
    private static final String CANT_STATUS_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 상태를 확인할 수 없습니다.";
    private static final String CANT_GET_RESULT_WHEN_NOW = "아직 승패를 판정할 수 없습니다.";
    private static final String NOT_INIT_CHESS_BOARD = "체스판이 아직 준비되지 않았습니다.";

    private final Board board;
    private final Camp camp;

    public Ready() {
        this.board = new Board();
        this.camp = Camp.WHITE;
    }

    @Override
    public State start() {
        return new Running(board, camp);
    }

    @Override
    public State move(final Positions positions) {
        throw new IllegalStateException(CANT_MOVE_WHEN_NOT_RUNNING);
    }

    @Override
    public State move(final Position before, final Position after) {
        throw new IllegalStateException(CANT_MOVE_WHEN_NOT_RUNNING);
    }

    @Override
    public Camp switchCamp() {
        throw new IllegalStateException(CANT_MOVE_WHEN_NOT_RUNNING);
    }

    @Override
    public State end() {
        return new Finished(board);
    }

    @Override
    public double statusOfBlack() {
        throw new IllegalStateException(CANT_STATUS_WHEN_NOT_RUNNING);
    }

    @Override
    public double statusOfWhite() {
        throw new IllegalStateException(CANT_STATUS_WHEN_NOT_RUNNING);
    }

    @Override
    public GameResult getResult() {
        throw new IllegalStateException(CANT_GET_RESULT_WHEN_NOW);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException(NOT_INIT_CHESS_BOARD);
    }
}
