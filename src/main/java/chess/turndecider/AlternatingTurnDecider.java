package chess.turndecider;

import chess.piece.Piece;

public class AlternatingTurnDecider implements TurnDecider {

    private State currentState = new WhiteState();

    public void setState(State state) {
        this.currentState = state;
    }

    private boolean isSameColor(Piece sourcePiece) {
        return currentState.isSameColor(sourcePiece);
    }

    public boolean isCorrectTurn(Piece sourcePiece) {
        boolean isCorrectTurn = isSameColor(sourcePiece);
        nextState();
        return isCorrectTurn;
    }

    private void nextState() {
        currentState = currentState.nextState();
    }
}
