package chess;

import chess.piece.Piece;
import chess.turndecider.State;
import chess.turndecider.TurnDecider;
import chess.turndecider.WhiteState;

public class FixedTurnDecider implements TurnDecider{

    private State currentState = new WhiteState();

    public void setState(State state) {

    }

    public boolean isCorrectTurn(Piece sourcePiece) {
        return true;
    }
}

