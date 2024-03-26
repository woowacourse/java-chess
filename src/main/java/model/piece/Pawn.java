package model.piece;

import model.Camp;

public abstract class Pawn extends Piece {

    private static final String PAWN_NAME = "p";

    protected Pawn(final Camp camp) {
        super(camp, PAWN_NAME, 1);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
