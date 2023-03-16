package chessgame.domain.state;

import chessgame.Command;

public interface State {
    void click(Button button, Command command);
}
