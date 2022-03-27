package chess.domain.game;

import java.util.Map;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

public class End implements State {
	@Override
	public State start() {
		return new End();
	}

	@Override
	public State end() {
		return new End();
	}

	@Override
	public State move(Coordinate from, Coordinate to) {
		return new End();
	}

	@Override
	public Map<Coordinate, Piece> getValue() {
		return null;
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
