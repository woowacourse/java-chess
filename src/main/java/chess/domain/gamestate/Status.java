package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.StatusScore;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public final class Status implements State {

    private static final String CANT_MOVE_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.";
    private static final String CANT_END_WHEN_STATUS = "status 상태에서는 end 명령어를 입력할 수 없습니다.";
    private static final String CANT_READY_WHEN_NOT_RUNNING = "게임 종료가 아닐때는 시작상태로 돌아갈 수 없습니다.";
    private static final String CANT_CHECK_KING_CHECKED_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 킹의 check여부를 확인할 수 없습니다.";
    private static final String CANT_CHECK_KING_CHECKMATED_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 킹의 checkmated여부를 확인할 수 없습니다.";

    private final Board board;
    private final Camp camp;

    public Status(final Board board, final Camp camp) {
        this.board = board;
        this.camp = camp;
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
        throw new IllegalStateException(CANT_MOVE_WHEN_NOT_RUNNING);
    }

    @Override
    public State status() {
        throw new IllegalStateException();
    }

    @Override
    public StatusScore calculateStatus() {
        return StatusScore.from(board);
    }

    @Override
    public State end() {
        throw new IllegalStateException(CANT_END_WHEN_STATUS);
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
    public List<Position> getKingCheckmatedPositions() {
        throw new IllegalStateException(CANT_CHECK_KING_CHECKMATED_WHEN_NOT_RUNNING);
    }

    @Override
    public boolean isAllKingCheckmated(final List<Position> positions) {
        throw new IllegalStateException(CANT_CHECK_KING_CHECKMATED_WHEN_NOT_RUNNING);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
