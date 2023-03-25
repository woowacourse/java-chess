package chess.domain.game.state;

public interface GameState {

    GameState start();

    GameState end();

    GameState run();

    String getStateName();

    boolean isStarted();
}
