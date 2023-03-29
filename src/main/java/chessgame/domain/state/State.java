package chessgame.domain.state;

import chessgame.controller.Command;
import chessgame.domain.Game;
import chessgame.domain.Team;

public interface State {
    static State from(String state) {
        if ("ready".equals(state)) {
            return new Ready();
        }
        if ("black".equals(state)) {
            return new BlackTurn();
        }
        if ("white".equals(state)) {
            return new WhiteTurn();
        }
        throw new IllegalStateException("잘못된 작동입니다.");
    }

    void changeState(Game game, Command command);

    boolean isRunning();

    boolean isNotEnd();

    Team team();

    String name();
}
