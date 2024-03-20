package chess.domain.state;

public interface GameState {
    GameState start();

    GameState move();

    GameState end();
}
