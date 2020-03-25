package domain.commend;

import domain.pieces.Pieces;

public class End extends NotPlaying {

	protected End(Pieces pieces) {
		super(pieces);
	}

	@Override
	public State start() {
		return null;
	}

	@Override
	public State status() {
		return null;
	}

	@Override
	public State pushCommend(String input) {
		return null;
	}
}
