package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.StatusScore;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.Piece;
import java.util.Map;

public class Finished implements State {

    private static final String CANT_MOVE_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.";
    private static final String CANT_SWITCH_CAMP_IF_NOT_RUNNING = "진행 중이 아닐 때는 턴(진영)을 바꿀 수 없습니다.";
    private static final String CANT_STATUS_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 상태를 확인할 수 없습니다.";
    private static final String CANT_END_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 종료할 수 없습니다.";
    private static final String CANT_CHECK_KING_CHECKED_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 킹의 check여부를 확인할 수 없습니다.";

    private final Board board;

    public Finished(Board board) {
        this.board = board;
    }

    @Override
    public State run() {
        return new Running(new Board());
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
        return StatusScore.from(board);
    }

    @Override
    public State end() {
        throw new IllegalStateException(CANT_END_WHEN_NOT_RUNNING);
    }

    @Override
    public State ready() {
        return new Ready();
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
        return true;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
