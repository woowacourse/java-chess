package chess.domain.gamestate;

import chess.domain.Winner;
import chess.domain.board.Board;
import chess.domain.board.Position;

public class Ready implements State {
	private static final String INVALID_STATE_MOVE_EXCEPTION = "게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.";
	private static final String INVALID_STATE_STATUS_EXCEPTION = "게임이 진행중이 아닐때는 상태를 확인할 수 없습니다.";
	private static final String INVALID_STATE_RESULT_EXCEPTION = "아직 승패를 판정할 수 없습니다.";
	private static final String INVALID_STATE_READY_TO_BOARD_EXCEPTION = "체스판이 아직 준비되지 않았습니다.";

	@Override
	public State start() {
		return new Running(new Board());
	}

	@Override
	public State move(Position beforePosition, Position afterPosition) {
		throw new IllegalStateException(INVALID_STATE_MOVE_EXCEPTION);
	}

	@Override
	public State end() {
		return this;
	}

	@Override
	public double statusOfBlack() {
		throw new IllegalStateException(INVALID_STATE_STATUS_EXCEPTION);
	}

	@Override
	public double statusOfWhite() {
		throw new IllegalStateException(INVALID_STATE_STATUS_EXCEPTION);
	}

	@Override
	public Winner findWinner() {
		throw new IllegalStateException(INVALID_STATE_RESULT_EXCEPTION);
	}

	@Override
	public boolean isRunning() {
		return false;
	}

	@Override
	public Board getBoard() {
		throw new IllegalStateException(INVALID_STATE_READY_TO_BOARD_EXCEPTION);
	}
}
