package domain.commend;

import domain.pieces.Pieces;

public class Waiting extends Commend {

    public Waiting(Pieces pieces) {
        super(pieces);
    }

    @Override
    public GameState start() {
        return new Playing(pieces);
    }

    @Override
    public GameState end() {
        return new Finished(pieces);
    }

    @Override
    public GameState move() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
