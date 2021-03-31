package chess.controller.command;

public interface Command {

    void execute();

    boolean isEnd();

    boolean isStart();
}
