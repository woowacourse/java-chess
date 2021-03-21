package chess.domain.command;

public interface Command {
    void handle(String input);

    boolean isAppropriateCommand(String input);

    boolean isStatus();
}
