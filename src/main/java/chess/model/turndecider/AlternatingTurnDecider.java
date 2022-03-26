package chess.model.turndecider;

import chess.model.piece.Piece;

public class AlternatingTurnDecider implements TurnDecider {

    private State currentState = new WhiteState();

    @Override
    public boolean isTurnOf(Piece Piece) {
        return isSameColor(Piece);
    }

    @Override
    public void nextState() {
        currentState = currentState.nextState();
    }

    private boolean isSameColor(Piece sourcePiece) {
        return currentState.isSameColor(sourcePiece);
    }
}
