package chess.domain.command;

public interface Command {
    Command execute(final String command);

    boolean isStart();

    boolean isEnd();

    boolean isMove();

    default Command ready() {
        return new Ready();
    }
}
