package chess.game;

public interface GameState {

    GameState start();

    GameState finish();

    boolean isRunnable();
}
