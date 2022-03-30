package chess.console.state;

import java.util.List;

public interface State {
    boolean isEnd();

    State run(List<String> inputs);
}
