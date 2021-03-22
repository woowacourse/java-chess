package chess.domain.command;

public interface Command {
    Command execute(final String command);

    boolean isStart();

    boolean isEnd();

    boolean isMove();

    boolean isStatus();

    default Command ready() {
        return new Ready();
    }

    default Command end() {
        return new End();
    }
}
