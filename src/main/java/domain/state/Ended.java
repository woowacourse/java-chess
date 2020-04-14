package domain.state;

import domain.pieces.Pieces;

public class Ended extends NotPlaying {
	public Ended(Pieces pieces) {
		super(StateType.ENDED, pieces);
	}
}
