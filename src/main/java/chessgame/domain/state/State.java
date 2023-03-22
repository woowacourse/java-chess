package chessgame.domain.state;

import chessgame.domain.Command;
import chessgame.domain.Game;
import chessgame.domain.Team;

public interface State {
    void changeState(Game game, Command command);

    boolean isRunning();

    Team team();
}
