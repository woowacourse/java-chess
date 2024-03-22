package model.piece;

import model.Camp;

public abstract class Pawn extends Piece {

    private static final String PAWN_NAME = "p";

    protected Pawn(final Camp camp) {
        super(camp, new PieceName(PAWN_NAME));
    }
}
