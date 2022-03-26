package chess.gamestate;

public interface GameState {

    GameState run(String requestCommand);

    boolean isEnd();
}
