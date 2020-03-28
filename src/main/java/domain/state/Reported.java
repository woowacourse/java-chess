package domain.state;

import domain.pieces.Pieces;

public class Reported extends NotPlaying {
    protected Reported(Pieces pieces) {
        super(pieces);
    }
}
