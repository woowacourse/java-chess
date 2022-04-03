package chess.console.domain.command;

public abstract class CommandState {

    public abstract boolean isStart();

    public abstract boolean isStatus();

    public abstract boolean isMove();

    public abstract boolean isEnd();

    public abstract CommandState execute(String command);
}
