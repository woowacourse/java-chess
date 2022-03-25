package chess.command;

import java.util.List;

public interface State {
    State turnState(String input);
    boolean isEnd();
    boolean isMove();
    List<String> getCommandPosition();
}
