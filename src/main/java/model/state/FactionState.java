package model.state;

import model.piece.Piece;

public interface FactionState {

    void checkSameFaction(final Piece piece);

    FactionState pass();
}
