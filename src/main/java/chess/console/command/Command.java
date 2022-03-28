package chess.console.command;

public interface Command {

    Command run(String command);

    boolean isEnd();
}
