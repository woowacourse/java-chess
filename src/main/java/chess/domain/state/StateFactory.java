package chess.domain.state;

import chess.domain.ChessBoardFactory;
import chess.domain.Side;

public class StateFactory {
	private StateFactory() {
	}

	public static State create() {
		return new Running(ChessBoardFactory.create(), Side.WHITE);
	}
}
