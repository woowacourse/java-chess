package domain.commend;

import domain.pieces.Pieces;

public class Start extends Playing {
	protected Start(Pieces pieces) {
		super(pieces);
	}

	@Override
	public State end() {
		return new End(getPieces());
	}
}
