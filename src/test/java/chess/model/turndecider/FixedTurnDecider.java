package chess.model.turndecider;

import chess.model.piece.Piece;

public class FixedTurnDecider implements TurnDecider {

    private final State currentState = new WhiteState();

    public boolean isCorrectTurn(Piece sourcePiece) {
        return true;
    }

    @Override
    public void nextState() {
    }
}

