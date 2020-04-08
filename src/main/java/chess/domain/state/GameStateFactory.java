package chess.domain.state;

import java.util.Arrays;
import java.util.function.BiFunction;

import chess.domain.board.Board;
import chess.domain.piece.Team;

public enum GameStateFactory {
	READY("ready", (turn, board) -> new Ready(new Board(board), Team.of(turn))),
	STARTED("started", (turn, board) -> new Started(new Board(board), Team.of(turn))),
	KING_CATCHED_FINISHED("king_catch_finished",
		(turn, board) -> new KingCatchFinished(new Board(board), Team.of(turn))),
	SUSPEND_FINISHED("suspend_finished", (turn, board) -> new SuspendFinished(new Board(board), Team.of(turn)));

	private final String state;
	private final BiFunction<String, String, GameState> gameStateGenerator;

	GameStateFactory(String state, BiFunction<String, String, GameState> gameStateGenerator) {
		this.state = state;
		this.gameStateGenerator = gameStateGenerator;
	}

	public static GameState of(String state, String turn, String board) {
		return Arrays.stream(values())
			.filter(val -> val.state.equals(state))
			.map(val -> val.gameStateGenerator.apply(turn, board))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않는 인자입니다."));
	}
}
