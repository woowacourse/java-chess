package model.state;

import model.piece.Piece;
import model.position.Position;

import java.util.Map;

public class ChessState {
    private FactionState factionState;

    public ChessState() {
        this.factionState = new WhiteFaction();
    }

    public void checkTheTurn(final Piece piece) {
        factionState.checkSameFaction(piece);
    }

    public void passTheTurn() {
        this.factionState = factionState.pass();
    }

    public void validateCheck(final Map<Position, Piece> chessBoard) {
        if (factionState.isCheck(chessBoard)) {
            factionState = factionState.check();
        }
    }
}
