package chess.console.command;

import java.util.List;

public interface State {
    Command turnState(String input);

    Command turnFinalState(String input);

    boolean isEnd();

    boolean isMove();

    boolean isStatus();

    List<String> getCommandPosition();
}
