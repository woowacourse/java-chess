package chess.domain.command;

public interface Command {
    void handle(String input);

    boolean isUsable(String input);

    boolean isStatus();
}
