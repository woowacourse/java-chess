package chess.domain.state;

import static chess.domain.state.StateType.*;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.result.Result;

public class SuspendFinished extends Finished {
	public SuspendFinished(Board board, Team turn) {
		super(board, SUSPEND_FINISHED, turn);
	}

	@Override
	public Team getWinner() {
		Result status = status();
		return status.findWinner();
	}
}
