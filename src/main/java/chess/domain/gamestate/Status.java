package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.StatusScore;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.TreeMap;

public final class Status implements State {

    private static final String DONT_START_WHEN_RUNNING = "진행 중일 때는 시작할 수 없습니다.";
    private static final String CANT_MOVE_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.";
    private static final String CANT_END_WHEN_STATUS = "status 상태에서는 end 명령어를 입력할 수 없습니다.";


    private final Board board;
    private final Camp camp;

    public Status(final Board board, final Camp camp) {
        this.board = board;
        this.camp = camp;
    }

    @Override
    public State start() {
        throw new IllegalStateException(DONT_START_WHEN_RUNNING);
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
    public State toRunningState() {
        return new Running(board, camp);
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
        return new TreeMap<>(board.getBoard());
    }
}
