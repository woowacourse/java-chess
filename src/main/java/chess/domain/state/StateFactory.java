package chess.domain.state;

import chess.domain.ChessBoardFactory;

public class StateFactory {
	private StateFactory() {
	}

	public static State create() {
		return new Running(ChessBoardFactory.create());
	}
}
