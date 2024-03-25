package model.state;

import model.piece.Piece;

public class ChessState {
    private FactionState factionState;

    public ChessState() {
        this.factionState = new WhiteFaction();
    }

    public void checkTheTurn(final Piece piece){
        factionState.checkSameFaction(piece);
    }

    public void passTheTurn() {
        this.factionState = factionState.pass();
    }
}
