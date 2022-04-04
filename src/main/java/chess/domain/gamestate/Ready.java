package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.StatusScore;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public class Ready implements State {

    private static final String CANT_MOVE_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.";
    private static final String CANT_SWITCH_CAMP_IF_NOT_RUNNING = "진행 중이 아닐 때는 턴(진영)을 바꿀 수 없습니다.";
    private static final String CANT_STATUS_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 상태를 확인할 수 없습니다.";
    private static final String NOT_INIT_CHESS_BOARD = "체스판이 아직 준비되지 않았습니다.";
    private static final String CANT_READY_WHEN_NOT_RUNNING = "게임 종료가 아닐때는 시작상태로 돌아갈 수 없습니다.";
    private static final String CANT_CHECK_KING_CHECKED_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 킹의 check여부를 확인할 수 없습니다.";

    private final Board board;
    private final Camp camp;

    public Ready() {
        this.board = new Board();
        this.camp = Camp.WHITE;
    }

    @Override
    public State run() {
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
        throw new IllegalStateException(CANT_SWITCH_CAMP_IF_NOT_RUNNING);
    }

    @Override
    public State status() {
        throw new IllegalStateException(CANT_STATUS_WHEN_NOT_RUNNING);
    }

    @Override
    public StatusScore calculateStatus() {
        throw new IllegalStateException(CANT_STATUS_WHEN_NOT_RUNNING);
    }

    @Override
    public State end() {
        return new Finished(board);
    }

    @Override
    public State ready() {
        throw new IllegalStateException(CANT_READY_WHEN_NOT_RUNNING);
    }

    @Override
    public boolean isKingChecked() {
        throw new IllegalStateException(CANT_CHECK_KING_CHECKED_WHEN_NOT_RUNNING);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new IllegalStateException(NOT_INIT_CHESS_BOARD);
    }

    @Override
    public List<Position> getKingCheckmatedPositions() {
        throw new UnsupportedOperationException("Ready#isKingCheckmated not implemented.");
    }

    @Override
    public boolean isAllKingCheckmated(final List<Position> positions) {
        throw new UnsupportedOperationException("Ready#isAllKingCheckmated not implemented.");
    }
}
