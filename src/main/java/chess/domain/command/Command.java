package chess.domain.command;

public interface Command {

    void execution(String text);

    boolean isMatchedCommand(String text);

}
