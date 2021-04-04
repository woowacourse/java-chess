package chess.domain.command;

public interface Command {
    void execute(String input);

    String getCommand();

    boolean isStatus();
}
