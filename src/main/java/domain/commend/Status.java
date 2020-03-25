package domain.commend;

import domain.pieces.Pieces;

public class Status extends NotPlaying {

    protected Status(Pieces pieces) {
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
}
