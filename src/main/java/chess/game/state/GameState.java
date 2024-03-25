package chess.game.state;

public interface GameState {

    GameState start();

    GameState proceedTurn(TurnAction action);

    GameState terminate();

    boolean isPlaying();

    default void validatePlaying() {
        if (!isPlaying()) {
            throw new UnsupportedOperationException("게임이 진행되고 있지 않습니다.");
        }
    }
}
