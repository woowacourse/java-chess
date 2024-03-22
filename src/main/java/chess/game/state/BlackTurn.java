package chess.game.state;

import chess.piece.Color;

public class BlackTurn extends TurnState {

    @Override
    public GameState proceedTurn(TurnAction action) {
        action.perform(Color.BLACK);
        return new WhiteTurn();
    }
}
