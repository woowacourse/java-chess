package domain.commend;

import domain.pieces.Pieces;

public abstract class Commend implements GameState {
    protected Pieces pieces;

    public Commend(Pieces pieces) {
        this.pieces = pieces;
    }

    @Override
    public Pieces pieces() {
        return pieces;
    }
}
