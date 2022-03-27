package chess.turndecider;

import chess.domain.piece.Piece;
import chess.turndecider.State;
import chess.turndecider.TurnDecider;
import chess.turndecider.WhiteState;

public class FixedTurnDecider implements TurnDecider {

    private final State currentState = new WhiteState();

    public boolean isCorrectTurn(Piece sourcePiece) {
        return true;
    }

    @Override
    public void nextState() {
    }
}

