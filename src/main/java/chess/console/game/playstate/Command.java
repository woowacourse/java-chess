package chess.console.game.playstate;

public interface Command {

    Command run(String command);

    boolean isEnd();
}
