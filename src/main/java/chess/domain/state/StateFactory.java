package chess.domain.state;

import chess.domain.ChessBoardFactory;
import chess.domain.Side;

public class StateFactory {
	public static State create(String inputCommand) {
		if ("start".equals(inputCommand)) {
			return new Running(ChessBoardFactory.create(), Side.WHITE);
		}
		return new End(ChessBoardFactory.create());
	}
}
