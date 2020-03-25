package domain.commend;

import domain.pieces.Pieces;
import domain.pieces.PiecesFactory;

public class End extends NotPlaying {

	protected End(Pieces pieces) {
		super(pieces);
	}

	@Override
	public State start() {
		return new Start(Pieces.of(new PiecesFactory()));
	}

	@Override
	public State status() {
		return new Status(getPieces());
	}

}
