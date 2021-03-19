package chess.domain.command;

public interface Command {
    String run(String input);

    boolean isSameCommand(String command);
}
