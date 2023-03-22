package chess.domain.game.state;

public interface GameState {
    boolean isRunnable();

    GameState start();

    GameState end();

    GameState move();
}
