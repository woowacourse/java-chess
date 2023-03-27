package domain.state;

import java.util.List;

public interface State {

    boolean isEnd();

    State run(List<String> command);
}
