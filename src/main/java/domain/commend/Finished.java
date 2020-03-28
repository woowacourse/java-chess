package domain.commend;

import domain.pieces.Pieces;

public class Finished extends Commend {

    public Finished(Pieces pieces) {
        super(pieces);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameState move() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
