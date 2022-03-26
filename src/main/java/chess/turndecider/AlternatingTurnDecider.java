package chess.turndecider;

import chess.piece.Piece;

public class AlternatingTurnDecider implements TurnDecider {

    private State currentState = new WhiteState();

    @Override
    public boolean isCorrectTurn(Piece sourcePiece) {
        return isSameColor(sourcePiece);
    }

    @Override
    public void nextState() {
        currentState = currentState.nextState();
    }

    private boolean isSameColor(Piece sourcePiece) {
        return currentState.isSameColor(sourcePiece);
    }
}
