package chess.game.state;

public interface GameState {

    GameState start();

    GameState proceedTurn(TurnAction action);

    GameState terminate();

    boolean isPlaying();
}
