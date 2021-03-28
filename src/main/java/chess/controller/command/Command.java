package chess.controller.command;

public interface Command {

    Command execute(final String command);

    boolean isExit();
}
