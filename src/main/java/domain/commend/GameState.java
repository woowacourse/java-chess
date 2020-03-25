package domain.commend;

import domain.pieces.Pieces;

public abstract class GameState implements State {
    private Pieces pieces;

    protected GameState(Pieces pieces) {
        this.pieces = pieces;
    }

    protected Pieces getPieces() {
        return pieces;
    }
}
