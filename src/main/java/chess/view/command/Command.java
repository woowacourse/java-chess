package chess.view.command;

public interface Command {

    Command run(String command);

    boolean isEnd();
}
