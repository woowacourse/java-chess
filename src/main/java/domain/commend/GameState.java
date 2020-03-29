package domain.commend;

import domain.pieces.Pieces;

public abstract class GameState implements StateStrategy {

    protected Pieces pieces;

    public GameState(Pieces pieces) {
        this.pieces = pieces;
    }

    @Override
    public Pieces pieces() {
        return pieces;
    }
}
