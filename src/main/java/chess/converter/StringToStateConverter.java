package chess.converter;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Finished;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.domain.state.RunningBlackTurn;
import chess.domain.state.RunningWhiteTurn;

public class StringToStateConverter {

	private static final String NOT_EXIST_STATE_NAME = "존재하지 않는 상태명입니다.";

	private static final String READY = "READY";
	private static final String RUNNING_WHITE = "RUNNING_WHITE";
	private static final String RUNNING_BLACK = "RUNNING_BLACK";
	private static final String FINISHED = "FINISHED";

	public static GameState of(String name, Map<Position, Piece> board) {
		if (name.equals(READY)) {
			return new Ready(board);
		}
		if (name.equals(RUNNING_WHITE)) {
			return new RunningWhiteTurn(board);
		}
		if (name.equals(RUNNING_BLACK)) {
			return new RunningBlackTurn(board);
		}
		if (name.equals(FINISHED)) {
			return new Finished(board);
		}
		throw new IllegalArgumentException(NOT_EXIST_STATE_NAME);
	}
}
