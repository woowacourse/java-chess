package domain.commend;

import domain.pieces.Pieces;

public class Playing extends Commend {

    public Playing(Pieces pieces) {
        super(pieces);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameState end() {
        return new Finished(pieces);
    }

    @Override
    public GameState move() {
        return this;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
