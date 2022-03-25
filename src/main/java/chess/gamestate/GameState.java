package chess.gamestate;

public interface GameState {

    GameState run(String command);

    boolean isEnd();
}
