package chess.console.game.playstate;

public interface PlayState {

    PlayState run(String command);

    boolean isEnd();
}
