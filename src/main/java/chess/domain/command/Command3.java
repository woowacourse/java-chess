package chess.domain.command;

public interface Command3 {
    void execution(String text);

    boolean isMatchedCommand(String text);

    boolean isStatus();
}
