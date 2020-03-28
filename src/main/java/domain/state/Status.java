package domain.state;

import domain.pieces.Pieces;

public class Status extends NotPlaying {
    protected Status(Pieces pieces) {
        super(pieces);
    }
}
