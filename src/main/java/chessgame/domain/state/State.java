package chessgame.domain.state;

import chessgame.domain.Command;

public interface State {
    void click(Button button, Command command);
}
