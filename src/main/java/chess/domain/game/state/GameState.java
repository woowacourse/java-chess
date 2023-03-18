package chess.domain.game.state;

public interface GameState {

    GameState start();

    GameState end();

    boolean isRunning();

    void move(Runnable runnable);
}
