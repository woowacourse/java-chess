package domain.state;

import domain.state.exceptions.StateException;
import domain.pieces.Pieces;
import domain.point.Point;

public abstract class Playing extends GameState {
	protected Playing(Pieces pieces) {
		super(pieces);
	}

	@Override
	public State start() {
		throw new StateException("Playing 상태에서 start 명령어는 유효하지 않습니다.");
	}

	@Override
	public State status() {
		throw new StateException("Playing 상태에서 status 명령어는 유효하지 않습니다.");
	}

	@Override
	public State move(String from, String to) {
		pieces.move(Point.of(from), Point.of(to));
		return new Move(pieces);
	}

	@Override
	public State end() {
		return new End(pieces);
	}
}
