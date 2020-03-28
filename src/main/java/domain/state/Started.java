package domain.state;

import domain.pieces.Pieces;

public class Started extends Playing {
	protected Started(Pieces pieces) {
		super(pieces);
	}
}
