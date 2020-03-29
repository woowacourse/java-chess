package domain.state;

import domain.pieces.Pieces;
import domain.point.Point;
import domain.state.exceptions.StateException;

public abstract class Playing extends GameState {
	protected Playing(Pieces pieces) {
		super(pieces);
	}

	@Override
	protected State start() {
		throw new StateException("Playing 상태에서 start 명령어는 유효하지 않습니다.");
	}

	@Override
	protected State status() {
		throw new StateException("Playing 상태에서 status 명령어는 유효하지 않습니다.");
	}

	@Override
	protected State move(String from, String to) {
		pieces.move(Point.of(from), Point.of(to));
		if (pieces.isKingKilled()) {
			return new Ended(pieces);
		}

		return new Moved(pieces);
	}

	@Override
	protected State end() {
		return new Ended(pieces);
	}
}
