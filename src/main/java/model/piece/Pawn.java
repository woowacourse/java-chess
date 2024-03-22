package model.piece;

import model.Camp;

public abstract class Pawn extends Piece {

    protected Pawn(final Camp camp) {
        super(camp, new PieceName("p"));
    }

    @Override
    public String toString() {
        return getName();
    }
}
