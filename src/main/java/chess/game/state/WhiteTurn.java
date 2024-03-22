package chess.game.state;

public class WhiteTurn extends TurnState {

    @Override
    public GameState proceedTurn(TurnAction action) {
        action.perform();
        return new BlackTurn();
    }
}
