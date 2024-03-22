package chess.game.state;

public class BlackTurn extends TurnState {

    @Override
    public GameState proceedTurn(TurnAction action) {
        action.perform();
        return new WhiteTurn();
    }
}
