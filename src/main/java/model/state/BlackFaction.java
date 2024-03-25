package model.state;

import model.piece.Color;
import model.piece.Piece;

public class BlackFaction implements FactionState {
    public static final Color COLOR = Color.BLACK;

    @Override
    public void checkSameFaction(final Piece piece) {
        if (piece.color() != COLOR) {
            throw new IllegalArgumentException("현재 해당 진영의 차례가 아닙니다.");
        }
    }

    @Override
    public FactionState pass() {
        return new WhiteFaction();
    }
}
