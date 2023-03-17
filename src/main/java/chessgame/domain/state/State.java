package chessgame.domain.state;

import chessgame.domain.Command;

public interface State {
    void click(Power power, Command command);
}
