package model.state;

import model.piece.Piece;

public sealed interface FactionState permits BlackFaction, WhiteFaction {

    void checkSameFaction(final Piece piece);

    FactionState pass();
}
