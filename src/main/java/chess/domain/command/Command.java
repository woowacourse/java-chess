package chess.domain.command;

public interface Command {
    void execute(String input);

    boolean isUsable(String input);

    boolean isStatus();
}
