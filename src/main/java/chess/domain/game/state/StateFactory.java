package chess.domain.game.state;

import java.util.function.BiFunction;

import chess.domain.game.Board;
import chess.domain.game.Turn;

public enum StateFactory {
	READY((board, turn) -> new Ready()),
	PLAYING((board, turn) -> new Playing(Board.from(board), Turn.from(turn))),
	FINISHED((board, turn) -> new Finished(Board.from(board), Turn.from(turn)));

	private final BiFunction<String, String, State> creator;

	StateFactory(BiFunction<String, String, State> creator) {
		this.creator = creator;
	}

	public State create(String board, String turn) {
		return creator.apply(board, turn);
	}
}
