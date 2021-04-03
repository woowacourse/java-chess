package chess.domain.command;

public interface Command {

    void execute(String input);

    boolean isStatus();
}
