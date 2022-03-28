package chess.turndecider;

import chess.domain.piece.Piece;
import chess.turndecider.state.State;
import chess.turndecider.state.WhiteTeam;

public class FixedTurnDecider implements TurnDecider {

    private final State currentState = new WhiteTeam();

    public boolean isCorrectTurn(Piece sourcePiece) {
        return true;
    }

    @Override
    public void nextState(boolean isFinished) {
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}

