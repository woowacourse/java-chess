package domain.commend;

import domain.commend.exceptions.StateException;
import domain.pieces.Pieces;

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
}
