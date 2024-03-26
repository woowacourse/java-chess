package model.state;

import model.piece.Color;
import model.piece.Piece;

public final class WhiteFaction implements FactionState {
    public static final Color COLOR = Color.WHITE;

    @Override
    public void checkSameFaction(Piece piece) {
        if (piece.color() != COLOR) {
            throw new IllegalArgumentException("현재 해당 진영의 차례가 아닙니다.");
        }
    }
    @Override
    public FactionState pass() {
        return new BlackFaction();
    }
}
