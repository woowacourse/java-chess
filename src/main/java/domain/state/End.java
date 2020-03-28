package domain.state;

import domain.pieces.Pieces;

public class End extends NotPlaying {
	public End(Pieces pieces) {
		super(pieces);
	}
}
