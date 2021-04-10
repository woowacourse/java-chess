package chess.domain.command;

public interface Command {

    void execute();

    boolean isEnd();

    boolean isMove();

    boolean isStatus();

    boolean isShow();

    boolean isRestart();
}
