package chess.model.turndecider;

import chess.model.piece.Piece;

public class FixedTurnDecider implements TurnDecider {

    private final State currentState = new WhiteState();

    public boolean isTurnOf(Piece Piece) {
        return true;
    }

    @Override
    public void nextState() {
    }
}

