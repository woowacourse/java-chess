package chess.game.state;

import chess.piece.Color;

public class WhiteTurn extends TurnState {

    @Override
    public GameState proceedTurn(TurnAction action) {
        action.perform(Color.WHITE);
        return new BlackTurn();
    }
}
