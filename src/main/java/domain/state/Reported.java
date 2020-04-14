package domain.state;

import domain.pieces.Pieces;

public class Reported extends NotPlaying {
    public Reported(Pieces pieces) {
        super(StateType.REPORTED, pieces);
    }
}
